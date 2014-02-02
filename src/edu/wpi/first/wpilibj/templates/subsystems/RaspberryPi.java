package edu.wpi.first.wpilibj.templates.subsystems;

import com.sun.squawk.util.StringTokenizer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 * Interface with a RaspberryPi, or any other Networked Computer(Cubieboard,
 * BeagleBone Black, Driver Station, ect.), over a TCP Socket Connection running
 * in its own thread. The Socket connection is placed into it's own thread and
 * must be interfaced through the static DataKeeper subclass.
 *
 * @author Gustave Michel
 */
public class RaspberryPi extends Subsystem {
    
    public static int xOff = 0;

    private String url = "socket://10.48.14.50:10000"; //change to use team's selected IP and Port
    private int bufferSize = 64; //If you need more bytes than this, go for it, but wow, lots of data.
    private char delimiter = ','; //The character used to separate data in the socket stream.

    private SocketConnection m_socket; //Connection from which the Input and Output streams are created
    private InputStream m_is;
    private OutputStream m_os;

    byte[] m_receivedData; //The bytes received from the Socket
    String m_rawData; //String data to be parsed

    private boolean m_connected = false; //if the pi is connected

    Thread m_thread;
    private boolean m_enabled = false;
    private boolean m_run = true;

    protected void initDefaultCommand() {
    }

    /**
     * Used to interface the RaspberryPi's Thread to the Robot's Subsystem and
     * Commands All fields need to be static, and all methods need to be
     * synchronized and static.
     */
    public static class DataKeeper {

        private static int m_x = 0; //data we are getting from the connection,
        private static int m_y = 0; //you can change to fit your purposes
        private static double m_width = 0d;
        private static double m_height = 0d;
        private static double m_time = 0; //when the last report was filed
        private static boolean m_report = false; //if a report was filed previously

        public static synchronized void setReport(boolean report) {
            m_report = report;
            SmartDashboard.putBoolean("PiReport", m_report);
        }

        public static synchronized void setX(int x) {
            m_x = x;
            SmartDashboard.putNumber("PiX", m_x);
        }

        public static synchronized void setY(int y) {
            m_y = y;
            SmartDashboard.putNumber("PiY", m_y);
        }

        public static synchronized void setWidth(double width) {
            m_width = width;
            SmartDashboard.putNumber("PiWidth", m_width);
        }

        public static synchronized void setHeight(double height) {
            m_height = height;
            SmartDashboard.putNumber("PiHeight", m_height);
        }

        public static synchronized void setTime(double time) {
            m_time = time;
            SmartDashboard.putNumber("PiTime", m_time);
        }

        public static synchronized boolean getReport() {
            return m_report;
        }

        public static synchronized int getX() {
            return m_x;
        }

        public static synchronized int getY() {
            return m_y;
        }

        public static synchronized double getWidth() {
            return m_width;
        }

        public static synchronized double getHeight() {
            return m_height;
        }

        public static synchronized double getTime() {
            return m_time;
        }
    }

    /**
     * The thread in which the RaspberryPi Socket Connection data acquisition
     * and parsing is run.
     */
    private class RaspberryPiThread extends Thread {

        public int x, y;
        public double width, height;
        public double time;
        private boolean report;

        /**
         * Init thread for the pi socket to run in
         *
         * @param raspberryPi Pi to execute in thread
         */
        public RaspberryPiThread() {
            super("RaspberryPiSocket");
        }

        public void run() {
            while (m_run) {
                if (CommandBase.raspberryPi.isEnabled()) { //Checks for Thread to run
                    if (CommandBase.raspberryPi.isConnected()) {
                        report = true;
                        try {
                            String[] data = CommandBase.raspberryPi.tokenizeData(CommandBase.raspberryPi.getRawData()); //Get and examine Data
                            time = Timer.getFPGATimestamp(); //Timestamp used to check if data was updated from outside thread (through DataKeeper)
                            if (data.length < 2) { //Error Check
                                report = false; //If a remote was made
                            } else {
                                try {
                                    x = Integer.parseInt(data[0]);
                                    y = Integer.parseInt(data[1]); //Get data and parse it to proper data types
                                    width = Double.parseDouble(data[2]);
                                    height = Double.parseDouble(data[3]);
                                } catch (NumberFormatException ex) {
                                    report = false;
                                }
                            }
                        } catch (IOException ex) {
                            report = false;
                        }
                        DataKeeper.setReport(report);

                        if (report) { //Store Data in DataKeeper
                            DataKeeper.setX(x);
                            DataKeeper.setY(y);
                            DataKeeper.setWidth(width);
                            DataKeeper.setHeight(height);
                            DataKeeper.setTime(time);
                        }
                    } else {
                        try {
                            CommandBase.raspberryPi.connect();
                        } catch (IOException ex) {
                            DataKeeper.setReport(false);
                        }
                    }
                }
                try {
                    Thread.sleep(375); //Wait half second before getting Data again
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    /**
     * Constructor
     */
    public RaspberryPi() {
        m_enabled = false;
        m_thread = new RaspberryPiThread();

        m_thread.start();
    }

    /**
     * Attempts to connect to the Socket Server
     *
     * @throws IOException
     */
    public synchronized void connect() throws IOException {
        m_socket = (SocketConnection) Connector.open(url);//, Connector.READ_WRITE, true);
        m_is = m_socket.openInputStream();
        m_os = m_socket.openOutputStream();
        m_connected = true;

    }

    /**
     * Used to safely close out the socket stream object before reconnecting,
     * this will not stop the thread from trying to re-connect.
     *
     * @throws IOException
     */
    public synchronized void disconnect() throws IOException {
        m_socket.close();
        m_is.close();
        m_os.close();
        m_connected = false;
    }

    /**
     * Checks if the Socket Connection is Open
     *
     * @return if the connection is available
     */
    public synchronized boolean isConnected() {
        //need to actually test the connection
        //to figure out if we're connected or not
        try {
            m_os.write('\n'); //request Data
            m_connected = true;
        } catch (IOException ex) {
            m_connected = false;
        } catch (Exception ex) {
            m_connected = false;

        }

        return m_connected;
    }

    /**
     * If the Socket Thread is running
     *
     * @return if the socket thread is running
     */
    public synchronized boolean isEnabled() {
        return m_enabled;
    }

    public int getX() {
        return DataKeeper.getX();
    }

    public int getY() {
        return DataKeeper.getY();
    }

    public double getWidth() {
        return DataKeeper.getWidth();
    }

    public double getHeight() {
        return DataKeeper.getHeight();
    }

    public double getTime() {
        return DataKeeper.getTime();
    }

    public boolean getReport() {
        return DataKeeper.getReport();
    }

    /**
     * Enables the Thread Execution
     */
    public synchronized void start() {
        m_enabled = true;
    }

    /**
     * Disables the Thread Execution
     */
    public synchronized void stop() {
        m_enabled = false;
    }

    /**
     * Requests data from RaspberryPi
     *
     * @return String returned from RaspberryPi
     * @throws IOException
     */
    public synchronized String getRawData() throws IOException {
        byte[] input; //temporary holder

        if (m_connected) {
            m_os.write('G'); //request Data
            System.out.println("Requested Data");

            if (m_is.available() <= bufferSize) {
                input = new byte[m_is.available()]; //storage space sized to fit!
                m_receivedData = new byte[m_is.available()]; //using stream to simply null detection
                m_is.read(input); //Read in data from Pi
                for (int i = 0; (input != null) && (i < input.length); i++) {
                    m_receivedData[i] = input[i]; //transfer input to full size storage
                }
            } else {
                System.out.println("PI OVERFLOW");
                m_is.skip(m_is.available()); //reset if more is stored than buffer
                return null;
            }

            m_rawData = ""; //String to transfer received data to
            System.out.println("Raw Data: " + m_receivedData.length);
            for (int i = 0; i < m_receivedData.length; i++) {
                m_rawData += (char) m_receivedData[i]; //Cast bytes to chars and concatinate them to the String
            }
            System.out.println(m_rawData);
            return m_rawData;
        } else {
            connect();
            return null;
        }
    }

    /**
     * Separates input String into many Strings based on the delimiter given
     * above
     *
     * @param input String to be tokenized
     * @return String Array of Tokenized Input String
     */
    public synchronized String[] tokenizeData(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input, String.valueOf(delimiter));
        String output[] = new String[tokenizer.countTokens()];

        for (int i = 0; i < output.length; i++) {
            output[i] = tokenizer.nextToken();
        }
        return output;
    }
}
