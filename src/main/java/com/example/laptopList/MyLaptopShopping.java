import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyLaptopShopping {
    private Connection connection;

    public MyLaptopShopping() {
        try {
            DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
            this.connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb", "SA", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getChoice() {
        int userInput = 0;
        MyLaptopShopping laptop = new MyLaptopShopping();
        Scanner scanner = new Scanner(System.in);
        String userChoice = "Y";
        while (userChoice.equalsIgnoreCase("Y")) {
            System.out.println("Option [1] - insert laptop details");
            System.out.println("Option [2] - delete laptop details");
            System.out.println("Option [3] - update laptop details");
            System.out.println("Option [4] - display laptop details");
            System.out.println("Option [5] - Exit");
            System.out.println("Choose an option from above: ");
            try {
                userInput = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please, select option [1-5].");
            }
            switch (userInput) {
                case 1 -> laptop.insert();
                case 2 -> laptop.delete();
                case 3 -> laptop.update();
                case 4 -> laptop.display();
                case 5 -> programExit();
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Enter 'Y' to continue \nEnter 'N' to exit.");
            userChoice = scanner.next();
        }
    }

    public void insert() {
        int laptopID = 0;
        String laptopName = "";
        String laptopBrand = "";
        double laptopPrice = 0.0;
        for (int i = 0; i < 1; i++) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Enter laptop id: ");
                laptopID = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid laptop Id!!! Please enter number only.");
                return;
            }
            if (!idIdValid(laptopID)) {
                System.out.println("Enter laptop name: ");
                laptopName = scanner.next();

                System.out.println("Enter laptop brand: ");
                laptopBrand = scanner.next();

                System.out.println("Enter laptop Price: ");
                try {
                    laptopPrice = scanner.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid amount!!, please do it again.");
                    break;
                }
                try {
                    PreparedStatement pst = this.connection.prepareStatement("insert into LAPTOP values (?,?,?,?)");
                    pst.setInt(1, laptopID);
                    pst.setString(2, laptopName);
                    pst.setString(3, laptopBrand);
                    pst.setDouble(4, laptopPrice);

                    int rows = pst.executeUpdate();
                    System.out.println("Rows inserted on Laptop Table : " + rows);
                    pst.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String data = laptopID + " " + laptopName + " " + laptopBrand + " " + laptopPrice;
                updateLogTable("insert", data);
            } else {
                System.out.println("Insert different laptop id.");
            }

        }
    }

    public void update() {
        int chosenId = 0;
        String updatedLaptopName = "";
        String updatedLaptopBrand = "";
        double updatedLaptopPrice = 0.00;
        try {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Enter laptop ID that you want to update details: ");
                chosenId = scanner.nextInt();
                scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Entered laptop id is Not a number!!!");
                return;
            }
            if (idIdValid(chosenId)) {
                PreparedStatement pst = this.connection.prepareStatement("UPDATE LAPTOP SET NAME=?, BRAND=?, PRICE=?  WHERE ID=? ");

                System.out.println("Enter updated laptop name:");
                updatedLaptopName = scanner.nextLine();

                System.out.println("Enter updated laptop brand:");
                updatedLaptopBrand = scanner.nextLine();

                System.out.println("Enter updated laptop Price:");
                updatedLaptopPrice = scanner.nextDouble();

                pst.setString(1, updatedLaptopName);
                pst.setString(2, updatedLaptopBrand);
                pst.setDouble(3, updatedLaptopPrice);
                pst.setInt(4, chosenId);

                int rows = pst.executeUpdate();
                System.out.println("Rows updated on Laptop Table: " + rows);
                pst.close();
                String data = chosenId + " " + updatedLaptopName + " " + updatedLaptopBrand + " " + updatedLaptopPrice;
                updateLogTable("update", data);
            } else {
                System.out.println("Laptop Id does not exit");
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }


    public void delete() {
        Scanner scanner = new Scanner(System.in);
        String data = "";
        int selectedLaptopId = 0;
        String laptopName = "";
        String laptopBrand = "";
        double laptopPrice = 0.0;
        int laptopID = 0;
        try {
            System.out.println("Enter the Laptop id you wish to delete: ");
            selectedLaptopId = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please, type valid laptop id only!!!");
        }

        boolean validId = idIdValid(selectedLaptopId);
        if (validId) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM LAPTOP  WHERE id=?");

                Statement st = this.connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM LAPTOP where ID = " + selectedLaptopId);
                rs.next();
                laptopID = rs.getInt(1);
                laptopName = rs.getString(2);
                laptopBrand = rs.getString(3);
                laptopPrice = rs.getDouble(4);

                data = laptopID + ", " + laptopName + ", " + laptopBrand + ", " + laptopPrice;
                rs.close();
                preparedStatement.setInt(1, selectedLaptopId);
                preparedStatement.executeUpdate();
                System.out.println(laptopID + " Id is deleted.");
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            updateLogTable("delete", data);
        }else{
            System.out.println("Please try again. ");
        }
    }

    public void display() {
        try {
            Statement st = this.connection.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM LAPTOP");
            System.out.println("<<<<<<<<<<<Printing laptop details from Database>>>>>>>>>>>>");
            while (rs.next()) {
                System.out.print(rs.getInt(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.println("$" + rs.getDouble(4));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM mylog");

            System.out.println("<<<<<<<<<<<<This data is from log table>>>>>>>>>>>>");
            while (rs.next()) {
                System.out.print("[" + rs.getInt(1));
                System.out.print("], " + rs.getString(2));
                System.out.print(", " + rs.getString(3));
                System.out.print(", " + rs.getDate(4));
                System.out.println(", " + rs.getString(5));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateLogTable(String actionType, String data) {
        int rows = 0;
        try {
            PreparedStatement pst = this.connection.prepareStatement("insert into myLog values (?,?,?,?,?)");
            pst.setInt(1, findMaxIDFromLogTable());
            pst.setString(2, "Vishnu");
            pst.setString(3, actionType);
            pst.setDate(4, new Date(System.currentTimeMillis()));
            pst.setString(5, data);
            rows = pst.executeUpdate();
            System.out.println("Rows inserted on mylog Table : " + rows);
            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int findMaxIDFromLogTable() {
        int logId = 0;
        try {
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery("select MAX(id) from mylog");
            rs.next();
            logId = rs.getInt(1);
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return logId + 1;
    }

    public void programExit() {
        System.out.println("Thank you.");
        try {
            this.connection.close();
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean idIdValid(int validateId) {
        try {
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery("select id from laptop where id =" + validateId);
            if (rs.next()) {
                return true;
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int getValidNumber(String inputValue) {
        int intInputValue = 0;
        try {
            Integer.parseInt(inputValue);
        } catch (InputMismatchException e) {
            System.out.println("Invalid Number!!!");
        }
        return intInputValue;
    }
}

/*
    public boolean idIdValid(int validateId) {
        boolean valid = false;
        try {
            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery("select id from laptop where id =" + validateId);
            if (!rs.next()) {
              //  System.out.println(validateId + " is not in laptop table.");
            } else {
                //rs.getInt(1);
                valid = true;
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("Please inter valid number ");
        }
        return valid;

    }

 */