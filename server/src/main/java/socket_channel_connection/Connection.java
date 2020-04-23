package socket_channel_connection;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SocketChannel;

public class Connection {
    public void write(byte[] bytes, SocketChannel socketChannel) {
        try {
            socketChannel.write(ByteBuffer.wrap(bytes));
        } catch (IOException | NotYetConnectedException | NonWritableChannelException e) {
            try {
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            System.err.println("Ошибка записи");
        }
    }

    public byte[] read(SocketChannel socketChannel) throws IOException {
        int quantityOfReadBytes = 0;
        int bufferSize = 10000000;
        ByteBuffer outBuffer = ByteBuffer.allocate(bufferSize);
        try {
            quantityOfReadBytes = socketChannel.read(outBuffer);
        } catch (IOException | NotYetConnectedException | NonReadableChannelException e) {
            try {
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            System.err.println("Ошибка чтения");
        }

        if (quantityOfReadBytes == -1) {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            System.err.println("Reached end-of-stream of channel: " + socketChannel.getRemoteAddress());
        }

        byte[] bytes = new byte[bufferSize];
        outBuffer.rewind();
        outBuffer.get(bytes, 0, quantityOfReadBytes);
        outBuffer.clear();

        return bytes;
    }
}
