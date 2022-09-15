/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package room.booking.system;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZacharyAZY
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    private static final Color DARKPURPLE = new Color(102, 102, 255);
    private static final Color LIGHTPURPLE = new Color(204, 204, 255);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";

    public MainPage() {
        initComponents();
        try {
            updateBookingTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "There is something wrong when fetching booking data.\n" + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Gradient Panel
    class jPanelGradient extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color2 = new Color(227, 127, 202);
            Color color1 = new Color(127, 154, 227);
            GradientPaint gp = new GradientPaint(0, 180, color1, 180, 0, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    //Get Current Date
    public Date getCurrentDate() {
        return new Date();
    }

    public void updateBookingTable() throws Exception {
        DefaultTableModel dtm = (DefaultTableModel) bookingTable.getModel();
        dtm.setRowCount(0);
        BookingManager bm = new BookingManager();
        ArrayList<Booking> bookingList = bm.getBookingList();
        Object[] row = new Object[10];
        for (Booking b : bookingList) {
            row[0] = b.bookingId;
            row[1] = b.roomId;
            row[2] = b.customerName;
            row[3] = b.ICNumber;
            row[4] = b.telephoneNumber;
            row[5] = b.email;
            row[6] = b.startDate;
            row[7] = b.endDate;
            row[8] = String.format("%.2f", b.price);
            row[9] = b.isCheckedOut;
            dtm.addRow(row);
        }
    }

    //Update Room Table Content
    public void updateRoomTable(LocalDate startDate, LocalDate endDate) throws Exception {
        DefaultTableModel dtm = (DefaultTableModel) roomTable.getModel();
        dtm.setRowCount(0);
        RoomManager rm = new RoomManager();
        BookingManager bm = new BookingManager();
        ArrayList<Booking> bookingList = bm.getBookingList();
        ArrayList<Room> roomList = rm.getRoomList();
        ArrayList<String> usedRoomId = new ArrayList<>();
        for (Booking b : bookingList) {
            boolean isAvailable = b.checkAvailability(startDate, endDate);
            if (!isAvailable) {
                usedRoomId.add(b.roomId);
            }
        }
        Object[] row = new Object[4];
        for (Room r : roomList) {
            if (!usedRoomId.contains(r.roomId)) {
                row[0] = r.roomId;
                row[1] = r.roomView;
                row[2] = r.roomSize;
                row[3] = String.format("%.2f", r.pricePerNight);
                dtm.addRow(row);
            }
        }
    }

    //update Staff Table Content
    public void updateStaffTable() throws Exception {
        StaffAccountManager sam = new StaffAccountManager();
        ArrayList<StaffAccount> staffList = sam.getStaffList();
        DefaultTableModel dtm = (DefaultTableModel) staffTable.getModel();
        dtm.setRowCount(0);
        String[] row = new String[3];
        for (StaffAccount sa : staffList) {
            row[0] = sa.staffId;
            row[1] = sa.staffName;
            String password = "";
            int passwordLength = sa.password.length();
            for (int i = 0; i < passwordLength; i++) {
                password += "*";
            }
            row[2] = password;
            dtm.addRow(row);
        }
    }

    //resetDateSelection
    public void resetCreateBooking() {
        //reset table
        DefaultTableModel dtm = (DefaultTableModel) roomTable.getModel();
        dtm.setRowCount(0);
        //reset Date Selection
        startDateField.setCalendar(null);
        endDateField.setCalendar(null);
        startDateField.setEnabled(true);
        endDateField.setEnabled(true);
        //Disable table Selection
        roomTable.setEnabled(false);
        //Enable & Disable button
        searchRoomBtn.setEnabled(true);
        resetCrtBookingBtn.setEnabled(false);
        createBookingBtn.setEnabled(false);
    }

    //ResetBookingManagement
    public void resetBookingManagement() {
        //Reset text field
        manageBookingIdField.setText("");
        manageRoomIdField.setText("");
        manageStartDateField.setText("");
        manageEndDateField.setText("");
        manageCustNameField.setText("");
        manageICNumField.setText("");
        manageTelephoneNumField.setText("");
        manageEmailField.setText("");
        managePriceField.setText("");
        manageCheckoutStatus.setSelected(false);
        //Reset Button
        searchBookingBtn.setEnabled(true);
        resetManageBookingBtn.setEnabled(false);
        updateBookingBtn.setEnabled(false);
        deleteBookingBtn.setEnabled(false);
        //Reset Search Booking
        manageBookingIdField.setEnabled(true);
        manageCustNameField.setEnabled(false);
        manageICNumField.setEnabled(false);
        manageTelephoneNumField.setEnabled(false);
        manageEmailField.setEnabled(false);
        managePriceField.setEnabled(false);
        manageCheckoutStatus.setEnabled(false);
    }

    //Reset manage staff field
    public void resetManageStaff() {
        DefaultTableModel dtm = (DefaultTableModel) staffTable.getModel();
        dtm.setRowCount(0);
        manageStaffIdField.setText("");
        manageStaffNameField.setText("");
        managePasswordField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manageStaffPanel = new javax.swing.JPanel();
        manageStaffIdLabel = new javax.swing.JLabel();
        manageStaffNameLabel = new javax.swing.JLabel();
        managePasswordLabel = new javax.swing.JLabel();
        manageStaffIdField = new javax.swing.JTextField();
        manageStaffNameField = new javax.swing.JTextField();
        managePasswordField = new javax.swing.JPasswordField();
        sidePane = new jPanelGradient();
        viewBookingsSidePane = new javax.swing.JPanel();
        viewBookingsLabel = new javax.swing.JLabel();
        createBookingSidePane = new javax.swing.JPanel();
        createBookingLabel = new javax.swing.JLabel();
        manageBookingSidePane = new javax.swing.JPanel();
        manageBookingLabel = new javax.swing.JLabel();
        manageStaffSidePane = new javax.swing.JPanel();
        manageStaffLabel = new javax.swing.JLabel();
        logOutSidePane = new javax.swing.JPanel();
        logOutLabel = new javax.swing.JLabel();
        mainContentPane = new javax.swing.JPanel();
        viewBookingTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookingTable = new javax.swing.JTable();
        bookingListLabel = new javax.swing.JLabel();
        createBookingTab = new javax.swing.JPanel();
        searchRoomBtn = new javax.swing.JButton();
        startDateLabel = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        startDateField = new com.toedter.calendar.JDateChooser();
        endDateField = new com.toedter.calendar.JDateChooser();
        resetCrtBookingBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        roomTable = new javax.swing.JTable();
        createBookingBtn = new javax.swing.JButton();
        manageBookingTab = new javax.swing.JPanel();
        manageBookingTitleLabel = new javax.swing.JLabel();
        manageBookingIdLabel = new javax.swing.JLabel();
        manageRoomIdLabel = new javax.swing.JLabel();
        manageStartDateLabel = new javax.swing.JLabel();
        manageEndDateLabel = new javax.swing.JLabel();
        manageCustNameLabel = new javax.swing.JLabel();
        manageTelephoneNumLabel = new javax.swing.JLabel();
        manageEmailLabel = new javax.swing.JLabel();
        managePriceLabel = new javax.swing.JLabel();
        manageCheckoutLabel = new javax.swing.JLabel();
        manageBookingIdLabel10 = new javax.swing.JLabel();
        manageBookingIdField = new javax.swing.JTextField();
        manageRoomIdField = new javax.swing.JTextField();
        manageStartDateField = new javax.swing.JTextField();
        manageEndDateField = new javax.swing.JTextField();
        manageCustNameField = new javax.swing.JTextField();
        manageICNumField = new javax.swing.JTextField();
        manageTelephoneNumField = new javax.swing.JFormattedTextField();
        manageEmailField = new javax.swing.JTextField();
        manageCheckoutStatus = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        searchBookingBtn = new javax.swing.JButton();
        updateBookingBtn = new javax.swing.JButton();
        deleteBookingBtn = new javax.swing.JButton();
        resetManageBookingBtn = new javax.swing.JButton();
        managePriceField = new javax.swing.JFormattedTextField();
        ManageStaffTab = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        staffTable = new javax.swing.JTable();
        staffListLabel = new javax.swing.JLabel();
        addStuffBtn = new javax.swing.JButton();
        editStaffBtn = new javax.swing.JButton();
        deleteStaffBtn = new javax.swing.JButton();

        manageStaffIdLabel.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        manageStaffIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageStaffIdLabel.setText("Staff ID:");

        manageStaffNameLabel.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        manageStaffNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageStaffNameLabel.setText("Staff Name:");

        managePasswordLabel.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        managePasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        managePasswordLabel.setText("Password:");

        manageStaffIdField.setEditable(false);
        manageStaffIdField.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        manageStaffIdField.setEnabled(false);
        manageStaffIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStaffIdFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout manageStaffPanelLayout = new javax.swing.GroupLayout(manageStaffPanel);
        manageStaffPanel.setLayout(manageStaffPanelLayout);
        manageStaffPanelLayout.setHorizontalGroup(
            manageStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageStaffPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(manageStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(managePasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageStaffIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageStaffNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(manageStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manageStaffIdField)
                    .addComponent(manageStaffNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(managePasswordField))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        manageStaffPanelLayout.setVerticalGroup(
            manageStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageStaffPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(manageStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manageStaffIdField, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(manageStaffIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(manageStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageStaffNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageStaffNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(manageStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(managePasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(managePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Page");
        setResizable(false);

        viewBookingsSidePane.setBackground(new java.awt.Color(102, 102, 255));
        viewBookingsSidePane.setPreferredSize(new java.awt.Dimension(200, 80));
        viewBookingsSidePane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewBookingsSidePaneMouseClicked(evt);
            }
        });

        viewBookingsLabel.setFont(new java.awt.Font("Californian FB", 3, 24)); // NOI18N
        viewBookingsLabel.setForeground(new java.awt.Color(204, 204, 255));
        viewBookingsLabel.setText("View Bookings");

        javax.swing.GroupLayout viewBookingsSidePaneLayout = new javax.swing.GroupLayout(viewBookingsSidePane);
        viewBookingsSidePane.setLayout(viewBookingsSidePaneLayout);
        viewBookingsSidePaneLayout.setHorizontalGroup(
            viewBookingsSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewBookingsSidePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewBookingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        viewBookingsSidePaneLayout.setVerticalGroup(
            viewBookingsSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewBookingsSidePaneLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(viewBookingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        createBookingSidePane.setBackground(new java.awt.Color(204, 204, 255));
        createBookingSidePane.setPreferredSize(new java.awt.Dimension(200, 78));
        createBookingSidePane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createBookingSidePaneMouseClicked(evt);
            }
        });

        createBookingLabel.setFont(new java.awt.Font("Californian FB", 3, 24)); // NOI18N
        createBookingLabel.setForeground(new java.awt.Color(102, 102, 255));
        createBookingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createBookingLabel.setText("Create Booking");

        javax.swing.GroupLayout createBookingSidePaneLayout = new javax.swing.GroupLayout(createBookingSidePane);
        createBookingSidePane.setLayout(createBookingSidePaneLayout);
        createBookingSidePaneLayout.setHorizontalGroup(
            createBookingSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createBookingSidePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(createBookingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        createBookingSidePaneLayout.setVerticalGroup(
            createBookingSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createBookingSidePaneLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(createBookingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        manageBookingSidePane.setBackground(new java.awt.Color(204, 204, 255));
        manageBookingSidePane.setPreferredSize(new java.awt.Dimension(200, 78));
        manageBookingSidePane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageBookingSidePaneMouseClicked(evt);
            }
        });

        manageBookingLabel.setFont(new java.awt.Font("Californian FB", 3, 24)); // NOI18N
        manageBookingLabel.setForeground(new java.awt.Color(102, 102, 255));
        manageBookingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageBookingLabel.setText("Manage Booking");

        javax.swing.GroupLayout manageBookingSidePaneLayout = new javax.swing.GroupLayout(manageBookingSidePane);
        manageBookingSidePane.setLayout(manageBookingSidePaneLayout);
        manageBookingSidePaneLayout.setHorizontalGroup(
            manageBookingSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageBookingSidePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageBookingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        manageBookingSidePaneLayout.setVerticalGroup(
            manageBookingSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageBookingSidePaneLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(manageBookingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        manageStaffSidePane.setBackground(new java.awt.Color(204, 204, 255));
        manageStaffSidePane.setPreferredSize(new java.awt.Dimension(200, 78));
        manageStaffSidePane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageStaffSidePaneMouseClicked(evt);
            }
        });

        manageStaffLabel.setFont(new java.awt.Font("Californian FB", 3, 24)); // NOI18N
        manageStaffLabel.setForeground(new java.awt.Color(102, 102, 255));
        manageStaffLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageStaffLabel.setText("Manage Staff");

        javax.swing.GroupLayout manageStaffSidePaneLayout = new javax.swing.GroupLayout(manageStaffSidePane);
        manageStaffSidePane.setLayout(manageStaffSidePaneLayout);
        manageStaffSidePaneLayout.setHorizontalGroup(
            manageStaffSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageStaffSidePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageStaffLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        manageStaffSidePaneLayout.setVerticalGroup(
            manageStaffSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageStaffSidePaneLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(manageStaffLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        logOutSidePane.setBackground(new java.awt.Color(204, 204, 255));
        logOutSidePane.setPreferredSize(new java.awt.Dimension(200, 78));
        logOutSidePane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutSidePaneMouseClicked(evt);
            }
        });

        logOutLabel.setFont(new java.awt.Font("Californian FB", 3, 24)); // NOI18N
        logOutLabel.setForeground(new java.awt.Color(102, 102, 255));
        logOutLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logOutLabel.setText("Log Out");

        javax.swing.GroupLayout logOutSidePaneLayout = new javax.swing.GroupLayout(logOutSidePane);
        logOutSidePane.setLayout(logOutSidePaneLayout);
        logOutSidePaneLayout.setHorizontalGroup(
            logOutSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logOutSidePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        logOutSidePaneLayout.setVerticalGroup(
            logOutSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logOutSidePaneLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(logOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout sidePaneLayout = new javax.swing.GroupLayout(sidePane);
        sidePane.setLayout(sidePaneLayout);
        sidePaneLayout.setHorizontalGroup(
            sidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(createBookingSidePane, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
            .addComponent(manageBookingSidePane, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
            .addComponent(viewBookingsSidePane, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
            .addComponent(manageStaffSidePane, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
            .addComponent(logOutSidePane, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        );
        sidePaneLayout.setVerticalGroup(
            sidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePaneLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(viewBookingsSidePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(createBookingSidePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(manageBookingSidePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(manageStaffSidePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(logOutSidePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        mainContentPane.setLayout(new java.awt.CardLayout());

        viewBookingTab.setBackground(new java.awt.Color(255, 153, 153));

        bookingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Room ID", "Customer Name", "IC Number/Passport", "Telephone No.", "Email", "Start Date", "End Date", "Price(RM)/night", "Checked Out"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookingTable.setRowHeight(25);
        jScrollPane1.setViewportView(bookingTable);

        bookingListLabel.setFont(new java.awt.Font("Pristina", 3, 36)); // NOI18N
        bookingListLabel.setForeground(new java.awt.Color(0, 102, 204));
        bookingListLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bookingListLabel.setText("Booking List");

        javax.swing.GroupLayout viewBookingTabLayout = new javax.swing.GroupLayout(viewBookingTab);
        viewBookingTab.setLayout(viewBookingTabLayout);
        viewBookingTabLayout.setHorizontalGroup(
            viewBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewBookingTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(viewBookingTabLayout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(bookingListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        viewBookingTabLayout.setVerticalGroup(
            viewBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewBookingTabLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(bookingListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        mainContentPane.add(viewBookingTab, "card2");

        createBookingTab.setBackground(new java.awt.Color(255, 204, 153));

        searchRoomBtn.setBackground(new java.awt.Color(0, 102, 255));
        searchRoomBtn.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        searchRoomBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchRoomBtn.setText("Search Room!");
        searchRoomBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchRoomBtn.setMargin(new java.awt.Insets(5, 14, 2, 14));
        searchRoomBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchRoomBtnActionPerformed(evt);
            }
        });

        startDateLabel.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        startDateLabel.setForeground(new java.awt.Color(0, 0, 0));
        startDateLabel.setText("Start Date:");

        endDateLabel.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        endDateLabel.setForeground(new java.awt.Color(0, 0, 0));
        endDateLabel.setText("End Date:");

        startDateField.setDateFormatString("dd-MM-yyyy");
        startDateField.setMinSelectableDate(getCurrentDate());

        endDateField.setDateFormatString("dd-MM-yyyy");
        endDateField.setMinSelectableDate(getCurrentDate());

        resetCrtBookingBtn.setBackground(new java.awt.Color(255, 153, 153));
        resetCrtBookingBtn.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        resetCrtBookingBtn.setForeground(new java.awt.Color(255, 255, 255));
        resetCrtBookingBtn.setText("Reset!");
        resetCrtBookingBtn.setEnabled(false);
        resetCrtBookingBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        resetCrtBookingBtn.setMargin(new java.awt.Insets(5, 14, 2, 14));
        resetCrtBookingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetCrtBookingBtnActionPerformed(evt);
            }
        });

        roomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room ID", "Room View", "Room Size (Person)", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        roomTable.setEnabled(false);
        roomTable.setRowHeight(25);
        roomTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        roomTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(roomTable);

        createBookingBtn.setBackground(new java.awt.Color(0, 204, 102));
        createBookingBtn.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        createBookingBtn.setForeground(new java.awt.Color(255, 255, 255));
        createBookingBtn.setText("Create Booking");
        createBookingBtn.setEnabled(false);
        createBookingBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createBookingBtn.setMargin(new java.awt.Insets(5, 14, 2, 14));
        createBookingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBookingBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createBookingTabLayout = new javax.swing.GroupLayout(createBookingTab);
        createBookingTab.setLayout(createBookingTabLayout);
        createBookingTabLayout.setHorizontalGroup(
            createBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createBookingTabLayout.createSequentialGroup()
                .addGroup(createBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createBookingTabLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(resetCrtBookingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(createBookingBtn))
                    .addGroup(createBookingTabLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(createBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(createBookingTabLayout.createSequentialGroup()
                                .addGroup(createBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(createBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(endDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(createBookingTabLayout.createSequentialGroup()
                                        .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(searchRoomBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)))))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        createBookingTabLayout.setVerticalGroup(
            createBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createBookingTabLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(createBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(startDateLabel)
                    .addComponent(endDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchRoomBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(createBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetCrtBookingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createBookingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        mainContentPane.add(createBookingTab, "card3");

        manageBookingTab.setBackground(new java.awt.Color(255, 255, 204));

        manageBookingTitleLabel.setFont(new java.awt.Font("Pristina", 3, 36)); // NOI18N
        manageBookingTitleLabel.setForeground(new java.awt.Color(0, 102, 204));
        manageBookingTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageBookingTitleLabel.setText("Booking Management");

        manageBookingIdLabel.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        manageBookingIdLabel.setForeground(new java.awt.Color(0, 0, 0));
        manageBookingIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageBookingIdLabel.setText("Booking ID:");

        manageRoomIdLabel.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        manageRoomIdLabel.setForeground(new java.awt.Color(0, 0, 0));
        manageRoomIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageRoomIdLabel.setText("Room ID:");

        manageStartDateLabel.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        manageStartDateLabel.setForeground(new java.awt.Color(0, 0, 0));
        manageStartDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageStartDateLabel.setText("Start Date:");

        manageEndDateLabel.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        manageEndDateLabel.setForeground(new java.awt.Color(0, 0, 0));
        manageEndDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageEndDateLabel.setText("End Date:");

        manageCustNameLabel.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        manageCustNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        manageCustNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageCustNameLabel.setText("Customer Name:");

        manageTelephoneNumLabel.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        manageTelephoneNumLabel.setForeground(new java.awt.Color(0, 0, 0));
        manageTelephoneNumLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageTelephoneNumLabel.setText("Telephone Number:");

        manageEmailLabel.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        manageEmailLabel.setForeground(new java.awt.Color(0, 0, 0));
        manageEmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageEmailLabel.setText("Email:");

        managePriceLabel.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        managePriceLabel.setForeground(new java.awt.Color(0, 0, 0));
        managePriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        managePriceLabel.setText("Price(RM):");

        manageCheckoutLabel.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        manageCheckoutLabel.setForeground(new java.awt.Color(0, 0, 0));
        manageCheckoutLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageCheckoutLabel.setText("Checkout Status:");

        manageBookingIdLabel10.setFont(new java.awt.Font("Californian FB", 1, 18)); // NOI18N
        manageBookingIdLabel10.setForeground(new java.awt.Color(0, 0, 0));
        manageBookingIdLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        manageBookingIdLabel10.setText("IC Number/ Passport:");

        manageBookingIdField.setDisabledTextColor(new java.awt.Color(51, 51, 51));

        manageRoomIdField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        manageRoomIdField.setEnabled(false);

        manageStartDateField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        manageStartDateField.setEnabled(false);

        manageEndDateField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        manageEndDateField.setEnabled(false);

        manageCustNameField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        manageCustNameField.setEnabled(false);

        manageICNumField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        manageICNumField.setEnabled(false);

        try {
            manageTelephoneNumField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        manageTelephoneNumField.setEnabled(false);

        manageEmailField.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        manageEmailField.setEnabled(false);

        manageCheckoutStatus.setBackground(new java.awt.Color(255, 255, 204));
        manageCheckoutStatus.setEnabled(false);
        manageCheckoutStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageCheckoutStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        manageCheckoutStatus.setPreferredSize(new java.awt.Dimension(22, 22));
        manageCheckoutStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageCheckoutStatusActionPerformed(evt);
            }
        });

        searchBookingBtn.setBackground(new java.awt.Color(0, 102, 255));
        searchBookingBtn.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        searchBookingBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBookingBtn.setText("Search Booking!");
        searchBookingBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchBookingBtn.setMargin(new java.awt.Insets(5, 14, 2, 14));
        searchBookingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBookingBtnActionPerformed(evt);
            }
        });

        updateBookingBtn.setBackground(new java.awt.Color(102, 102, 255));
        updateBookingBtn.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        updateBookingBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBookingBtn.setText("Update!");
        updateBookingBtn.setEnabled(false);
        updateBookingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingBtnActionPerformed(evt);
            }
        });

        deleteBookingBtn.setBackground(new java.awt.Color(153, 51, 0));
        deleteBookingBtn.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        deleteBookingBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBookingBtn.setText("Delete Booking");
        deleteBookingBtn.setEnabled(false);
        deleteBookingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBookingBtnActionPerformed(evt);
            }
        });

        resetManageBookingBtn.setBackground(new java.awt.Color(255, 153, 153));
        resetManageBookingBtn.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        resetManageBookingBtn.setForeground(new java.awt.Color(255, 255, 255));
        resetManageBookingBtn.setText("Reset!");
        resetManageBookingBtn.setEnabled(false);
        resetManageBookingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetManageBookingBtnActionPerformed(evt);
            }
        });

        managePriceField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#######.00"))));
        managePriceField.setEnabled(false);
        managePriceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managePriceFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout manageBookingTabLayout = new javax.swing.GroupLayout(manageBookingTab);
        manageBookingTab.setLayout(manageBookingTabLayout);
        manageBookingTabLayout.setHorizontalGroup(
            manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageBookingTabLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(manageBookingTabLayout.createSequentialGroup()
                        .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageBookingTabLayout.createSequentialGroup()
                                .addGap(0, 74, Short.MAX_VALUE)
                                .addComponent(manageBookingIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(manageBookingIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(searchBookingBtn)))
                        .addGap(25, 25, 25))
                    .addGroup(manageBookingTabLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(manageEmailLabel)
                            .addComponent(managePriceLabel)
                            .addComponent(manageStartDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(manageEndDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(manageCustNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(manageTelephoneNumLabel)
                            .addComponent(manageBookingIdLabel10)
                            .addComponent(manageCheckoutLabel)
                            .addComponent(manageRoomIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(manageStartDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(manageRoomIdField, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(manageEndDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(manageCustNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(manageICNumField, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(manageEmailField, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(manageCheckoutStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(manageTelephoneNumField)
                            .addComponent(managePriceField, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(manageBookingTabLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(resetManageBookingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(updateBookingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteBookingBtn)
                        .addGap(55, 55, 55))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageBookingTabLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(manageBookingTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
        );
        manageBookingTabLayout.setVerticalGroup(
            manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageBookingTabLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(manageBookingTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageBookingIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageBookingIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBookingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageRoomIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageRoomIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageStartDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageStartDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageEndDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageEndDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageCustNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageCustNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageICNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageBookingIdLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageTelephoneNumField, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(manageTelephoneNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(managePriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(managePriceField, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manageCheckoutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(manageCheckoutStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(manageBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetManageBookingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBookingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBookingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        mainContentPane.add(manageBookingTab, "card5");

        ManageStaffTab.setBackground(new java.awt.Color(204, 255, 204));

        staffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Staff ID", "Staff Name", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        staffTable.setRowHeight(25);
        staffTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        staffTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(staffTable);

        staffListLabel.setFont(new java.awt.Font("Pristina", 3, 36)); // NOI18N
        staffListLabel.setForeground(new java.awt.Color(0, 102, 204));
        staffListLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        staffListLabel.setText("Staff List");

        addStuffBtn.setBackground(new java.awt.Color(0, 204, 102));
        addStuffBtn.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        addStuffBtn.setForeground(new java.awt.Color(255, 255, 255));
        addStuffBtn.setText("Add Staff");
        addStuffBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStuffBtnActionPerformed(evt);
            }
        });

        editStaffBtn.setBackground(new java.awt.Color(102, 102, 255));
        editStaffBtn.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        editStaffBtn.setForeground(new java.awt.Color(255, 255, 255));
        editStaffBtn.setText("Edit Staff");
        editStaffBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editStaffBtnActionPerformed(evt);
            }
        });

        deleteStaffBtn.setBackground(new java.awt.Color(153, 51, 0));
        deleteStaffBtn.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        deleteStaffBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteStaffBtn.setText("Delete Staff");
        deleteStaffBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStaffBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ManageStaffTabLayout = new javax.swing.GroupLayout(ManageStaffTab);
        ManageStaffTab.setLayout(ManageStaffTabLayout);
        ManageStaffTabLayout.setHorizontalGroup(
            ManageStaffTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageStaffTabLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(addStuffBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editStaffBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(deleteStaffBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ManageStaffTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(ManageStaffTabLayout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addComponent(staffListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ManageStaffTabLayout.setVerticalGroup(
            ManageStaffTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageStaffTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(staffListLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ManageStaffTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStuffBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editStaffBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteStaffBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainContentPane.add(ManageStaffTab, "card7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainContentPane, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainContentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createBookingSidePaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createBookingSidePaneMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) mainContentPane.getLayout();
        cardLayout.show(mainContentPane, "card3");
        //Select Effect
        createBookingSidePane.setBackground(DARKPURPLE);
        createBookingLabel.setForeground(LIGHTPURPLE);
        //Unselect Effect
        viewBookingsSidePane.setBackground(LIGHTPURPLE);
        viewBookingsLabel.setForeground(DARKPURPLE);
        manageBookingSidePane.setBackground(LIGHTPURPLE);
        manageBookingLabel.setForeground(DARKPURPLE);
        manageStaffSidePane.setBackground(LIGHTPURPLE);
        manageStaffLabel.setForeground(DARKPURPLE);
    }//GEN-LAST:event_createBookingSidePaneMouseClicked

    private void viewBookingsSidePaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBookingsSidePaneMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) mainContentPane.getLayout();
        cardLayout.show(mainContentPane, "card2");
//        bookingManagementTab.setVisible(false);
//        bookingManagementTab.setVisible(true);
        //Select Effect
        viewBookingsSidePane.setBackground(DARKPURPLE);
        viewBookingsLabel.setForeground(LIGHTPURPLE);
        //Unselect Effect
        createBookingSidePane.setBackground(LIGHTPURPLE);
        createBookingLabel.setForeground(DARKPURPLE);
        manageBookingSidePane.setBackground(LIGHTPURPLE);
        manageBookingLabel.setForeground(DARKPURPLE);
        manageStaffSidePane.setBackground(LIGHTPURPLE);
        manageStaffLabel.setForeground(DARKPURPLE);

        //update Booking Table
        try {
            updateBookingTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "There is something wrong when fetching booking data.\n" + e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_viewBookingsSidePaneMouseClicked

    private void manageBookingSidePaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageBookingSidePaneMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) mainContentPane.getLayout();
        cardLayout.show(mainContentPane, "card5");
        //Select Effect
        manageBookingSidePane.setBackground(DARKPURPLE);
        manageBookingLabel.setForeground(LIGHTPURPLE);
        //Unselect Effect
        createBookingSidePane.setBackground(LIGHTPURPLE);
        createBookingLabel.setForeground(DARKPURPLE);
        viewBookingsSidePane.setBackground(LIGHTPURPLE);
        viewBookingsLabel.setForeground(DARKPURPLE);
        manageStaffSidePane.setBackground(LIGHTPURPLE);
        manageStaffLabel.setForeground(DARKPURPLE);

    }//GEN-LAST:event_manageBookingSidePaneMouseClicked

    private void manageStaffSidePaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageStaffSidePaneMouseClicked
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) mainContentPane.getLayout();
        cardLayout.show(mainContentPane, "card7");
        //Select Effect
        manageStaffSidePane.setBackground(DARKPURPLE);
        manageStaffLabel.setForeground(LIGHTPURPLE);
        //Unselect Effect
        createBookingSidePane.setBackground(LIGHTPURPLE);
        createBookingLabel.setForeground(DARKPURPLE);
        viewBookingsSidePane.setBackground(LIGHTPURPLE);
        viewBookingsLabel.setForeground(DARKPURPLE);
        manageBookingSidePane.setBackground(LIGHTPURPLE);
        manageBookingLabel.setForeground(DARKPURPLE);
        try {
            updateStaffTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_manageStaffSidePaneMouseClicked

    private void addStuffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStuffBtnActionPerformed
        // TODO add your handling code here:
        try {
            StaffAccountManager sam = new StaffAccountManager();
            String newStaffId = sam.getNewStaffID();
            manageStaffIdField.setText(newStaffId);
            manageStaffNameField.setText("");
            managePasswordField.setText("");
            int result = JOptionPane.showConfirmDialog(this, manageStaffPanel, "Add New Staff", JOptionPane.OK_CANCEL_OPTION, -1);
            if (result == JOptionPane.OK_OPTION) {
                String staffName = manageStaffNameField.getText();
                String password = String.valueOf(managePasswordField.getPassword());
                if (staffName.isBlank() || password.isBlank()) {
                    resetManageStaff();
                    JOptionPane.showMessageDialog(this, "You cannot leave any field blank.", "Error Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    sam.addStaff(newStaffId, staffName, password);
                    resetManageStaff();
                    JOptionPane.showMessageDialog(this, "Staff ID: " + newStaffId + " has been created sucessfully.", "Success Messsage", JOptionPane.INFORMATION_MESSAGE);
                }
                updateStaffTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_addStuffBtnActionPerformed

    private void logOutSidePaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutSidePaneMouseClicked
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(this, "Do you want to log out?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            dispose();
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new LoginPage().setVisible(true);
                }
            });
        }
    }//GEN-LAST:event_logOutSidePaneMouseClicked

    private void manageStaffIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStaffIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manageStaffIdFieldActionPerformed

    private void deleteStaffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStaffBtnActionPerformed
        // TODO add your handling code here:
        boolean isNotSelected = staffTable.getSelectionModel().isSelectionEmpty();
        if (!isNotSelected) {
            int selectedRow = staffTable.getSelectedRow();
            String targetId = staffTable.getModel().getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to delete staff: " + targetId + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    StaffAccountManager sam = new StaffAccountManager();
                    boolean result = sam.deleteStaff(targetId);
                    if (result) {
                        JOptionPane.showMessageDialog(this, "Staff ID: " + targetId + " has been deleted.", "Success Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    updateStaffTable();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "There is something wrong when deleting the staff.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "You need to select a row to delete a staff.", "Warning Message", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_deleteStaffBtnActionPerformed

    private void editStaffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editStaffBtnActionPerformed
        // TODO add your handling code here:
        boolean isNotSelected = staffTable.getSelectionModel().isSelectionEmpty();
        if (!isNotSelected) {
            int selectedRow = staffTable.getSelectedRow();
            String targetId = staffTable.getModel().getValueAt(selectedRow, 0).toString();
            try {
                StaffAccountManager sam = new StaffAccountManager();
                StaffAccount targetAccount = sam.searchStaff(targetId);
                if (targetAccount != null) {
                    manageStaffIdField.setText(targetId);
                    manageStaffNameField.setText(targetAccount.staffName);
                    managePasswordField.setText(targetAccount.password);
                    int result = JOptionPane.showConfirmDialog(this, manageStaffPanel, "Edit Staff: " + targetId, JOptionPane.OK_CANCEL_OPTION, -1);
                    if (result == JOptionPane.OK_OPTION) {
                        String newStaffName = manageStaffNameField.getText();
                        String newPassword = String.valueOf(managePasswordField.getPassword());
                        sam.updateStaff(targetId, newStaffName, newPassword);
                        JOptionPane.showMessageDialog(this, "Staff ID: " + targetId + " has been updated successfully.", "Success Message", JOptionPane.INFORMATION_MESSAGE);
                        updateStaffTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Staff not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You need to select a row to delete a staff.", "Warning Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editStaffBtnActionPerformed

    private void searchRoomBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchRoomBtnActionPerformed
        // Initialize
        LocalDate bookingStartDate, bookingEndDate;
        LocalDate now = LocalDate.now();
        Date startDate = startDateField.getDate();
        Date endDate = endDateField.getDate();
        //Validation
        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(this, "You must select a state date and an end date to view available rooms", "Warning Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        bookingStartDate = LocalDate.parse(SDF.format(startDateField.getDate()));
        bookingEndDate = LocalDate.parse(SDF.format(endDateField.getDate()));
        if (bookingStartDate.isBefore(now) || bookingEndDate.isBefore(now)) {
            JOptionPane.showMessageDialog(this, "You must select valid dates.", "Warning Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (bookingEndDate.isBefore(bookingStartDate)) {
            JOptionPane.showMessageDialog(this, "You must select valid dates.", "Warning Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            updateRoomTable(bookingStartDate, bookingEndDate);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "There is something wrong when fetching room data\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Setup
        startDateField.setEnabled(false);
        endDateField.setEnabled(false);
        searchRoomBtn.setEnabled(false);
        roomTable.setEnabled(true);
        resetCrtBookingBtn.setEnabled(true);
        createBookingBtn.setEnabled(true);
    }//GEN-LAST:event_searchRoomBtnActionPerformed

    private void resetCrtBookingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetCrtBookingBtnActionPerformed
        resetCreateBooking();
    }//GEN-LAST:event_resetCrtBookingBtnActionPerformed

    private void createBookingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBookingBtnActionPerformed
        boolean isNotSelected = roomTable.getSelectionModel().isSelectionEmpty();
        if (!isNotSelected) {
            LocalDate startDate = LocalDate.parse(SDF.format(startDateField.getDate()));
            LocalDate endDate = LocalDate.parse(SDF.format(endDateField.getDate()));
            int selectedRow = roomTable.getSelectedRow();
            String targetId = roomTable.getModel().getValueAt(selectedRow, 0).toString();
            String newBookingId = "";
            try {
                BookingManager bm = new BookingManager();
                newBookingId = bm.getNewBookingID();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "There is something wrong when obtaining new BookingID.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            new newBookingForm(newBookingId, targetId, startDate, endDate).setVisible(true);
            this.setVisible(false);
            resetCreateBooking();
        } else {
            JOptionPane.showMessageDialog(this, "You need to select a row(room) to create booking.", "Warning Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_createBookingBtnActionPerformed

    private void searchBookingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBookingBtnActionPerformed
        String targetBookingId = manageBookingIdField.getText();
        if (targetBookingId.isBlank()) {
            JOptionPane.showMessageDialog(this, "You need to input Booking ID to search for a booking.", "Warning Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        targetBookingId = targetBookingId.toUpperCase();
        try {
            BookingManager bm = new BookingManager();
            Booking targetBooking = bm.searchBooking(targetBookingId);
            if (targetBooking == null) {
                JOptionPane.showMessageDialog(this, "No booking is found with the Booking ID provided.", "Error Message", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // setup environment
            searchBookingBtn.setEnabled(false);
            manageBookingIdField.setEnabled(false);
            manageCustNameField.setEnabled(true);
            manageICNumField.setEnabled(true);
            manageTelephoneNumField.setEnabled(true);
            manageEmailField.setEnabled(true);
            managePriceField.setEnabled(true);
            manageCheckoutStatus.setEnabled(true);
            resetManageBookingBtn.setEnabled(true);
            updateBookingBtn.setEnabled(true);
            deleteBookingBtn.setEnabled(true);
            //input Data
            manageRoomIdField.setText(targetBooking.roomId);
            manageStartDateField.setText(String.valueOf(targetBooking.startDate));
            manageEndDateField.setText(String.valueOf(targetBooking.endDate));
            manageCustNameField.setText(targetBooking.customerName);
            manageICNumField.setText(targetBooking.ICNumber);
            manageTelephoneNumField.setText(targetBooking.telephoneNumber);
            manageEmailField.setText(targetBooking.email);
            managePriceField.setText(String.format("%.2f", targetBooking.price));
            manageCheckoutStatus.setSelected(targetBooking.isCheckedOut);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "There is something wrong when obtaining booking data.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchBookingBtnActionPerformed

    private void updateBookingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBookingBtnActionPerformed
        // TODO add your handling code here:
        String bookingId = manageBookingIdField.getText().toUpperCase();
        String updatedCustName = manageCustNameField.getText();
        String updatedICNum = manageICNumField.getText();
        String updatedTelNum = manageTelephoneNumField.getText();
        String updatedEmail = manageEmailField.getText();
        String rawUpdatedPrice = managePriceField.getText();
        boolean updatedCheckoutStatus = manageCheckoutStatus.isSelected();
        double updatedPrice;
        if (updatedCustName.isBlank()) {
            JOptionPane.showMessageDialog(this, "You cannot leave customer name field blank", "Warning Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (updatedICNum.isBlank()) {
            JOptionPane.showMessageDialog(this, "You cannot leave IC Number field blank", "Warning Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (updatedTelNum.replace("-", "").isBlank()) {
            JOptionPane.showMessageDialog(this, "You cannot leave Telephone Number field blank", "Warning Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (updatedEmail.isBlank()) {
            JOptionPane.showMessageDialog(this, "You cannot leave Email field blank", "Warning Message", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            boolean isValidEmail = Pattern.matches(EMAIL_REGEX, updatedEmail);
            if (!isValidEmail) {
                JOptionPane.showMessageDialog(this, "Please input a valid email", "Warning Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        if (rawUpdatedPrice.isBlank()) {
            JOptionPane.showMessageDialog(this, "You cannot leave Price field blank", "Warning Message", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            try {
                updatedPrice = Double.parseDouble(rawUpdatedPrice);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please input a valid price", "Warning Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        try {
            BookingManager bm = new BookingManager();
            bm.updateBooking(bookingId, updatedCustName, updatedICNum, updatedTelNum, updatedEmail, updatedPrice, updatedCheckoutStatus);
            JOptionPane.showMessageDialog(this, "Booking ID: " + bookingId + " has been updated.", "Success Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "There is something wrong when updating booking data.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateBookingBtnActionPerformed

    private void deleteBookingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBookingBtnActionPerformed
        String targetId = manageBookingIdField.getText().toUpperCase();
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure to delete Booking ID: " + targetId + " ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                BookingManager bm = new BookingManager();
                boolean result = bm.deleteBooking(targetId);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Booking ID: " + targetId + " has been deleted.", "Success Message", JOptionPane.INFORMATION_MESSAGE);
                    resetBookingManagement();
                    resetCreateBooking();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "There is something wrong when deleting booking.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteBookingBtnActionPerformed

    private void resetManageBookingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetManageBookingBtnActionPerformed
        //reset
        resetBookingManagement();
    }//GEN-LAST:event_resetManageBookingBtnActionPerformed

    private void manageCheckoutStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageCheckoutStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manageCheckoutStatusActionPerformed

    private void managePriceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managePriceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_managePriceFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ManageStaffTab;
    private javax.swing.JButton addStuffBtn;
    private javax.swing.JLabel bookingListLabel;
    private javax.swing.JTable bookingTable;
    private javax.swing.JButton createBookingBtn;
    private javax.swing.JLabel createBookingLabel;
    private javax.swing.JPanel createBookingSidePane;
    private javax.swing.JPanel createBookingTab;
    private javax.swing.JButton deleteBookingBtn;
    private javax.swing.JButton deleteStaffBtn;
    private javax.swing.JButton editStaffBtn;
    private com.toedter.calendar.JDateChooser endDateField;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JPanel logOutSidePane;
    private javax.swing.JPanel mainContentPane;
    private javax.swing.JTextField manageBookingIdField;
    private javax.swing.JLabel manageBookingIdLabel;
    private javax.swing.JLabel manageBookingIdLabel10;
    private javax.swing.JLabel manageBookingLabel;
    private javax.swing.JPanel manageBookingSidePane;
    private javax.swing.JPanel manageBookingTab;
    private javax.swing.JLabel manageBookingTitleLabel;
    private javax.swing.JLabel manageCheckoutLabel;
    private javax.swing.JCheckBox manageCheckoutStatus;
    private javax.swing.JTextField manageCustNameField;
    private javax.swing.JLabel manageCustNameLabel;
    private javax.swing.JTextField manageEmailField;
    private javax.swing.JLabel manageEmailLabel;
    private javax.swing.JTextField manageEndDateField;
    private javax.swing.JLabel manageEndDateLabel;
    private javax.swing.JTextField manageICNumField;
    private javax.swing.JPasswordField managePasswordField;
    private javax.swing.JLabel managePasswordLabel;
    private javax.swing.JFormattedTextField managePriceField;
    private javax.swing.JLabel managePriceLabel;
    private javax.swing.JTextField manageRoomIdField;
    private javax.swing.JLabel manageRoomIdLabel;
    private javax.swing.JTextField manageStaffIdField;
    private javax.swing.JLabel manageStaffIdLabel;
    private javax.swing.JLabel manageStaffLabel;
    private javax.swing.JTextField manageStaffNameField;
    private javax.swing.JLabel manageStaffNameLabel;
    private javax.swing.JPanel manageStaffPanel;
    private javax.swing.JPanel manageStaffSidePane;
    private javax.swing.JTextField manageStartDateField;
    private javax.swing.JLabel manageStartDateLabel;
    private javax.swing.JFormattedTextField manageTelephoneNumField;
    private javax.swing.JLabel manageTelephoneNumLabel;
    private javax.swing.JButton resetCrtBookingBtn;
    private javax.swing.JButton resetManageBookingBtn;
    private javax.swing.JTable roomTable;
    private javax.swing.JButton searchBookingBtn;
    private javax.swing.JButton searchRoomBtn;
    private javax.swing.JPanel sidePane;
    private javax.swing.JLabel staffListLabel;
    private javax.swing.JTable staffTable;
    private com.toedter.calendar.JDateChooser startDateField;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JButton updateBookingBtn;
    private javax.swing.JPanel viewBookingTab;
    private javax.swing.JLabel viewBookingsLabel;
    private javax.swing.JPanel viewBookingsSidePane;
    // End of variables declaration//GEN-END:variables
}
