

    import java.util.Scanner;
    import javax.swing.*;
    
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.File;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    
    //------------------------------------------------------------------------//
    class Passenger {
        String passegr_id;
        String passengr_name;
        String passenger_Gender;
        String passenger_destination;
        int passenger_phone_no;
        byte passeger_age;
        JFrame fram = new JFrame();
        Scanner input = new Scanner(System.in);
        BufferedWriter bf;
    
    
        void passeger_information() throws IOException {
            int min = 1;
            int max = 50;
            int j=0;
            j++;
          
    
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
    
    
            JOptionPane.showMessageDialog(fram, "The  Seat number of  ["+j+"] Passenger  is : "+random_int, "MESSAGE  ", JOptionPane.INFORMATION_MESSAGE);
            try {
                BufferedWriter bw = new BufferedWriter(
                        new FileWriter("E:\\airline project\\PassengerInformation.txt", true));
                System.out.println("Enter your id ");
                passegr_id = input.nextLine();
                System.out.println("Enter your name ");
                passengr_name = input.nextLine();
                System.out.println("Enter your Gender ");
                passenger_Gender = input.nextLine();
                System.out.println("Enter your phone number  ");
                passenger_phone_no = input.nextInt();
                System.out.println("Enter your age ");
                passeger_age = input.nextByte();
    
                bw.write("_______________\n");
                bw.write("passenger id : " + passegr_id + "\n");
                bw.write("passenger name : " + passengr_name + "\n");
                bw.write("passenger phone : " + passenger_phone_no + "\n");
                bw.write("passenger age : " + passeger_age + "\n");
                bw.write("passenger Gender : " + passenger_Gender + "\n");
                bw.write("passenger destination : " + passenger_destination + "\n");
                bw.write("passenger seat number : " + random_int + "\n");
                bw.write("_______________\n");
                bw.newLine();
                bw.close();
            } catch (Exception e) {
                System.out.println("Exception occurs at passenger method file writer ");
                e.printStackTrace();
            }
        }
    
    }
    
    // ------------------------------------------------------------------------//
    class tickett {
        JFrame fram = new JFrame();
    
        Passenger p;
    
        tickett(Passenger p) {
            this.p = p;
        }
    
        synchronized void getticket(int ticket) {
            int total_tickets = 50;
            if (total_tickets > ticket) {
    
                total_tickets = total_tickets - ticket;
                System.out.println("---------------------------");
                System.out.println("you can book " + ticket + " ticket ");
                // System.out.println("total in block "+total_tickets);
    
                try {
                    System.out.println("please Enter deatials of  passenger : ");
    
                    for (int i = 0; i < ticket; i++) {
                        System.out.println(" \n Enter the details of [" + (i + 1) + "] passenger \n ");
                        p.passeger_information();
    
                    }
                    JOptionPane.showMessageDialog(fram, " Data saved ", " MESSAGE ", JOptionPane.CLOSED_OPTION);
    
                } catch (IOException e) {
                    e.printStackTrace();
    
                }
    
            } else if (total_tickets < ticket) {
                System.out.print("SORRY! we have total tickets " + total_tickets);
    
            }
    
        }
    }
    // ------------------------------------------------------------------------//
    
    public class AirlineMain extends Thread {
        int seats;
        static tickett t;
        Scanner input = new Scanner(System.in);
    
        public void run() {
            System.out.println("Enter how many seats you want ");
            seats = input.nextInt();
            t.getticket(seats);
    
        }
    
        public static void main(String[] args) {
    
            JFrame frame = new JFrame();
            int air_option = 0;
            char ch = 'l';
    
            do {
    
                System.out.println("-----------------------------------");
                System.out.println("            WELCOME                ");
                System.out.println("-----------------------------------");
                Scanner sc = new Scanner(System.in);
                System.out.println(" 1 - AirLine information ");
                System.out.println(" 2 -  Book tickets ");
                System.out.println(" 3-  Search passenger  ");
                System.out.println(" 4-   Cancle ticket  ");
                System.out.println(" 5- Details of passengers avialable ");
    
                System.out.println(" please  Enter your  option ");
                int option = sc.nextInt();
    
                int choice;
                switch (option) {
    
                    case 1:
                        try {
                            File f = new File("E:\\airline project\\airlineinformation.txt");
                            BufferedReader bw = new BufferedReader(new FileReader(f));
                            String s;
                            while ((s = bw.readLine()) != null) {
                                System.out.println(s + "   ");
                            }
    
                            bw.close();
    
                        }
    
                        catch (Exception e) {
                            e.printStackTrace();
                        }
    
                        do {
                            System.out.println("Enter y to go back ");
                            ch = sc.next().charAt(0);
                            ScreenClear.clrscr();
                        } while (ch != 'y');
    
                        break;
                    // ==--------------------------------------------------------------------------==
                    case 2:
                        do {
                            System.out.println("where would you want to go : ");
                            System.out.println(" 1 - Lahore ");
                            System.out.println(" 2 - Islamabad ");
                            System.out.println(" 3 - Peshawar ");
                            System.out.println("PLEASE! Enter values of your choice : ");
                            choice = sc.nextInt();

    
                            if (choice > 3 || choice < 1) {
                                JOptionPane.showMessageDialog(frame, "You enter wrong digit ", " WARNING ",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } while (choice > 3 || choice < 1);
    
                        if (choice == 1) {
    
                            Passenger p1 = new Passenger();
                            t = new tickett(p1);
                            AirlineMain a = new AirlineMain();
                            p1.passenger_destination="lahore";
                           
                            a.start();
                            try {
                                a.join();
                            
                            } catch (InterruptedException e1) {
                                System.out.println("----------------------------------");
                                e1.printStackTrace();
                            }
    
                        }

                    else if(choice==2){

                        Passenger p2 = new Passenger();
                        t = new tickett(p2);
                        AirlineMain a = new AirlineMain();
                        p2.passenger_destination="islamabad";
                        a.start();
                        try {
                            a.join();
                        } catch (InterruptedException e1) {
                            System.out.println("Exception at jion method in case 2");
                            e1.printStackTrace();
                        }



                    }
                    else if(choice==3){

                        Passenger p3 = new Passenger();
                        t = new tickett(p3);
                        AirlineMain a = new AirlineMain();
                        p3.passenger_destination="peshawar";
                        a.start();
                        try {
                            a.join();
                        } catch (InterruptedException e1) {
                            System.out.println("Exception at jion method in case 2");
                            e1.printStackTrace();
                        }



                    }
                    
                 
    
                        do {
    
                            System.out.println("------------->Enter y to go back-----------> ");
                            ch = sc.next().charAt(0);
                            ScreenClear.clrscr();
                            if (ch != 'y') {
                                JOptionPane.showMessageDialog(frame, "PLEASE! Enter valid character ");
                            }
                        } while (ch != 'y');
    
                        break;
                    // ==-------------------------------------------------------------===========
                    case 3:
                        try {
                            System.out.println("  Enter passenger  id for search ");
                            String str = sc.next();
    
                            BufferedReader bfr = new BufferedReader(
                                    new FileReader("E:\\airline project\\PassengerInformation.txt"));
                            String s;
                            System.out.print("\n        LOADING    ");
                            for (int i = 0; i < 4; i++) {
                                try {
                                    Thread.sleep(1000);
                                    System.out.print(". ");
                                    Thread.yield();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            ScreenClear.clrscr();
    
                            System.out.println();
                            while ((s = bfr.readLine()) != null) {
                                if (s.contains(str)) {
                                    while ((s = bfr.readLine()) != null) {
    
                                        System.out.println(s);
    
                                        if (s.charAt(0) == ' ') {
                                            break;
                                        }
    
                                    }
    
                                }
    
                            }
    
                            bfr.close();
                        } catch (Exception e) {
                            e.getMessage();
                        }
    
                        do {
    
                            System.out.println("  Enter y to go back ");
                            ch = sc.next().charAt(0);
                            ScreenClear.clrscr();
                            if (ch != 'y') {
                                JOptionPane.showMessageDialog(frame, "PLEASE! Enter valid character ");
                            }
                        } while (ch != 'y');
    
                        break;
                    case 4:
                        Passenger p = new Passenger();
                        try {
                            System.out.println("Enter id to remove ");
                            String str = sc.next();
                            BufferedReader bfr = new BufferedReader(
                                    new FileReader("E:\\airline project\\PassengerInformation.txt"));
                           
                            String s;
                            System.out.print("\n            LOADING    ");
                            for (int i = 0; i < 3; i++) {
                                try {
                                    Thread.sleep(1000);
                                    System.out.print(".");
                                    Thread.yield();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            ScreenClear.clrscr();
                            
                            while ((s = bfr.readLine()) != null) {
                                if (s.contains(str)) {
                                    JOptionPane.showMessageDialog(frame, "Ticket cancled ");
                                    System.out.println();
                                    while ((s = bfr.readLine()) != null) {
                                        System.out.println(s);
                                        if (s.charAt(0) == ' ') {
                                            break;
                                        }
                                        
                                    }
                                    
                                }
                            }
                            
                           
                            bfr.close();
    
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        
                        do {
    
                            System.out.println("  Enter y to go back ");
                            ch = sc.next().charAt(0);
                            ScreenClear.clrscr();
                            if (ch != 'y') {
                                JOptionPane.showMessageDialog(frame, "PLEASE! Enter valid character ");
                            }
                        } while (ch != 'y');
    
                
                        break;
                    case 5:
    
                        try {
                            BufferedReader bfr = new BufferedReader(
                                    new FileReader("E:\\airline project\\PassengerInformation.txt"));
    
                            String s;
                            System.out.print("\n      Loading ");
    
                            for (int i = 0; i <= 3; i++) {
                                try {
                                    Thread.sleep(1000);
                                    System.out.print(". ");
                                    Thread.yield();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                          ScreenClear.clrscr();
                            while ((s = bfr.readLine()) != null) {
                                System.out.println(s);
    
                            }
    
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        
                        do {
    
                            System.out.println("  Enter y to go back ");
                            ch = sc.next().charAt(0);
                            ScreenClear.clrscr();
                            if (ch != 'y') {
                                JOptionPane.showMessageDialog(frame, "PLEASE! Enter valid character ");
                            }
                        } while (ch != 'y');
    
                    
    
                        break;
    
                    default:
                        JOptionPane.showMessageDialog(frame, " you have Entered the  wrong number   ", "WARNING",
                                JOptionPane.WARNING_MESSAGE);
                        System.out.println("Enter valid number ");
                        air_option = 4;
    
                }
    
            } while (air_option == 4 || ch == 'y');
    
        }
    }
    class ScreenClear {
        public static void clrscr() {
    
            try {
    
                if (System.getProperty("os.name").contains("Windows"))
    
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    
                else
    
                    Runtime.getRuntime().exec("clear");
    
            } catch (IOException | InterruptedException ex) {
            }
    
        }
    }
    

