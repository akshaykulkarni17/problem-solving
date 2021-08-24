class Data {
    private String packet;
    private boolean transfer = true;

    synchronized void send(String packet) throws InterruptedException {
        while (!transfer) {
            wait();
        }
        transfer = false;
        System.out.println("Sending " + packet);
        this.packet = packet;
        notify();
    }

    synchronized String receive() throws InterruptedException {
        while (transfer) {
            wait();
        }
        transfer = true;
        notify();
        return packet;
    }

}
public class PubSub {
    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] packets= new String[]{"first", "second", "third", "fourth", "end"};
                for (String packet : packets){
                    try {
                        data.send(packet);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread receiver = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (String packet=data.receive();!"end".equals(packet);packet= data.receive()){
                        System.out.println("Receiving "+packet);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        sender.start();
        receiver.start();
    }
}
