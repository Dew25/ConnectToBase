/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectToBase;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
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
        String url = "jdbc:mysql://localhost:3306/javaToBase";
        //Имя пользователя БД
        String name = "javaToBase";
        //Пароль
        String password = "javaToBase";
        try {
            //Загружаем драйвер
            Class.forName("org.mysql.Driver");
            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = (Connection) DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");
            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
            Statement statement = (Statement) connection.createStatement();
            //Выполним запрос
            ResultSet result1 = statement.executeQuery(
                    "SELECT * FROM student ");
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать 
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Выводим statement");
            while (result1.next()) {
                System.out.println("Номер в выборке #" + result1.getRow()
                        + "\t Номер в базе #" + result1.getInt("id")
                        + "\t" + result1.getString("name")
                        + "\t" + result1.getString("surname")
                        + "\t" + result1.getString("code"));
            }
            
            // Вставить запись
            
            
        } catch (Exception e) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(TestConnectToBase.class.getName()).log(Level.SEVERE, null, e);
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(TestConnectToBase.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
}
