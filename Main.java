import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try{

            Class.forName("com.mysql.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost:3306/Book";
            String DB_USER = "root";
            String DB_PASS = "";

            Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);

            Statement statement = conn.createStatement();

            System.out.println("1 Add a new book");
            System.out.println("2. Search book by id");
            System.out.println("3. Search book by name");
            System.out.println("4. Get all book");
            System.out.println("5. Update a book");
            System.out.println("6. Remove a book");
            System.out.println("7. Export a book to txt file");
            System.out.println("8. Export list book to list txt file");
            System.out.println("9. Read a book with Auto scroll");

            System.out.println("Input your choice: ");
            Scanner scanner = new Scanner(System.in);
            Scanner choice = new Scanner(System.in);
            int chon = choice.nextInt();

            while (chon >= 1 && chon<=9){

                switch(chon) {

                    case 1: {

                        System.out.println("Input title book");
                        String title = scanner.nextLine();
                        System.out.println("Input author book");
                        String author = scanner.nextLine();
                        System.out.println("Input release date");
                        String releaseDate = scanner.nextLine();
                        System.out.println("Input content book");
                        String conent = scanner.nextLine();

                        String sql ="SELECT * FROM lib";
                        String addBook = "INSERT INTO lib (title,author,releaseDate,content) VALUES ('"+ title +"','"+ author +"','"+ releaseDate +"','"+ conent +"')";
                        statement.executeUpdate(addBook);
                        System.out.println("Add a new book success!");
                        break;

                    }
                    case 2: {

                        System.out.println("Input id for search");
                        int id =scanner.nextInt();
                        String sql = "SELECT * FROM lib WHERE id = " + id;
                        ResultSet resultSet = statement.executeQuery(sql);
                        while(resultSet.next()){
                            System.out.println("Name of book: " + resultSet.getString("title") + "Author: " + resultSet.getString("author") + "Contetn: " + resultSet.getString("content"));
                        }
                        break;

                    }
                    case 3: {

                        System.out.println("input Name of book for search");
                        String name = scanner.nextLine();
                        String sql = "SELECT * FROM lib WHERE name = " + name;
                        ResultSet resultSet = statement.executeQuery(sql);
                        while (resultSet.next()){
                            System.out.println("Name of book: " + resultSet.getString("title") + "Author: " + resultSet.getString("author") + "Content: " + resultSet.getString("content"));
                        }
                        break;

                    }
                    case 4: {

                        String sql = "SELECT * FROM lib";
                        ResultSet resultSet = statement.executeQuery(sql);
                        while (resultSet.next()){
                            System.out.println("Name of book: " + resultSet.getString("title") + "Author: " + resultSet.getString("author") + "Contetn: " + resultSet.getString("content"));
                        }
                        break;

                    }
                    case 5:{

                        System.out.println("Input Id for Update");
                        int id = scanner.nextInt();
                        System.out.println("Name of book");
                        String title = scanner.nextLine();
                        String query = "UPDATE lib SET title = WHERE id = ? ";
                        PreparedStatement preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setInt(1, id);
                        preparedStatement.setString(2, title);
                        preparedStatement.executeUpdate();
                        conn.close();
                        System.out.println("Update success");
                        break;

                    }
                    case 6:{

                        System.out.println("Input id for remove book");
                        int id = scanner.nextInt();
                        String sql = "DELETE FROM lib WHERE id = " + id;
                        statement.execute(sql);
                        System.out.println("Remove success");
                        break;

                    }
                    case 7:{
                        System.out.println("Choice a book to export to txt file by id: ");
                        int id = scanner.nextInt();
                        statement = conn.createStatement();
                        String sql = "SELECT * FROM lib WHERE id = " + id;
                        ResultSet rec = statement.executeQuery(sql);
                        String path = "Z:\\SEM 2\\Store Book ASM\\aBook.txt";
                        FileWriter writer;
                        try{
                            File file = new File(path);
                            writer = new FileWriter(file, true);
                            while ((rec!=null) && (rec.next())){
                                writer.write(rec.getString("id"));
                                writer.write(",");
                                writer.write(rec.getString("title"));
                                writer.write(",");
                                writer.write(rec.getString("author"));
                                writer.write(",");
                                writer.write(rec.getString("releaseDate"));
                                writer.write(",");
                                writer.write(rec.getString("content"));
                                writer.write("\r\n");
                            }
                            writer.close();
                            System.out.println("Export to txt file success!");
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                        try{
                            if(conn !=null){
                                statement.close();
                                conn.close();
                            }
                        }catch (SQLException e){
                            e.printStackTrace();
                        }


                    }
                    case 8:{

                        statement = conn.createStatement();
                        String sql = "SELECT * FROM lib ORDER BY id ASC";
                        ResultSet rec = statement.executeQuery(sql);

                        String path = "Z:\\SEM 2\\Store Book ASM\\StoreBookAsm.txt";
                        FileWriter writer;
                        try{
                            File file = new File(path);
                            writer = new FileWriter(file, true);
                            while ((rec!=null) && (rec.next())){
                                writer.write(rec.getString("id"));
                                writer.write(",");
                                writer.write(rec.getString("title"));
                                writer.write(",");
                                writer.write(rec.getString("author"));
                                writer.write(",");
                                writer.write(rec.getString("releaseDate"));
                                writer.write(",");
                                writer.write(rec.getString("content"));
                                writer.write("\r\n");
                            }
                            writer.close();
                            System.out.println("Export to txt file success!");
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                        try{
                            if(conn !=null){
                                statement.close();
                                conn.close();
                            }
                        }catch (SQLException e){
                            e.printStackTrace();
                        }

                    }
                    case 9: {
                        break;
                    }
                }

            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
