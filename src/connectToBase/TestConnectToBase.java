/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectToBase;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jvm
 */
public class TestConnectToBase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection connection = null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        String url = "jdbc:mysql://localhost:3306/connectToBase";
        //Имя пользователя БД
        String name = "connectToBase";
        //Пароль
        String password = "connectToBase";
        try {
            //Загружаем драйвер
            //Class.forName("org.mysql.Driver");
            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = (Connection) DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");
            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
            //Statement statement = (Statement) connection.createStatement();
            //Выполним запрос
//            ResultSet result1 = statement.executeQuery(
//                    "SELECT * FROM student WHERE 1");
//            //result это указатель на первую строку с выборки
//            //чтобы вывести данные мы будем использовать 
//            //метод next() , с помощью которого переходим к следующему элементу
//            System.out.println("Выводим statement");
//            while (result1.next()) {
//                System.out.println("Номер в выборке #" + result1.getRow()
//                        + "\t Номер в базе #" + result1.getInt("id")
//                        + "\t" + result1.getString("name")
//                        + "\t" + result1.getString("surname")
//                        + "\t" + result1.getString("code"));
//            }
            
            // Вставить запись
//            statement.executeUpdate(
//                    "INSERT INTO student(name,surname,code) values('Peter','Petrov', '50012131123')");
            //Обновить запись
//            statement.executeUpdate(
//                    "UPDATE student SET name = 'Nikolay',surname = 'Nikolayev',code = '38206232231' where id = 2");
//            
            //------------------ Защищаем запросы от SQL инъекций ----------------
            //2.PreparedStatement: предварительно компилирует запросы, 
            //которые могут содержать входные параметры
            PreparedStatement preparedStatement;
            // ? - место вставки нашего значеня
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM student where id > ? and id < ?");
            //Устанавливаем в нужную позицию значения определённого типа
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, 5);
            //выполняем запрос
            ResultSet result2 = preparedStatement.executeQuery();
            
            System.out.println("Выводим PreparedStatement");
            while (result2.next()) {
                System.out.println("Номер в выборке #" + result2.getRow()
                        + "\t Номер в базе #" + result2.getInt("id")
                        + "\t" + result2.getString("name")
                        + "\t" + result2.getString("surname")
                        + "\t" + result2.getString("code"));
            }
            
//            preparedStatement = connection.prepareStatement(
//                    "INSERT INTO student(name,surname,code) values(?,?,?)");
//            preparedStatement.setString(1, "Fedja");
//            preparedStatement.setString(2, "Fjodorov");
//            preparedStatement.setString(3, "39709103312");
//            //метод принимает значение без параметров
//            //темже способом можно сделать и UPDATE
//            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(TestConnectToBase.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TestConnectToBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
