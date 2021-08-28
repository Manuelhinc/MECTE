package utp.misiontic2022.c2.p53.reto4.modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utp.misiontic2022.c2.p53.reto4.modelo.vo.Requerimiento_3;
import utp.misiontic2022.c2.p53.reto4.util.JDBCUtilities;

public class Requerimiento_3Dao {
    public ArrayList<Requerimiento_3> requerimiento3() throws SQLException {
        // Su código
        ArrayList<Requerimiento_3> respuesta = new ArrayList<Requerimiento_3>();
        Connection conexion = JDBCUtilities.getConnection();
        String consulta="select Cargo, MAX(l.Salario) FROM Lider l GROUP BY Cargo HAVING MAX(l.Salario)>700000";

        try {
            // Recorrer los registros en los VO específicos
            Statement statement=conexion.createStatement();
            ResultSet derivado=statement.executeQuery(consulta);
            while(derivado.next()){
                Requerimiento_3 requerimiento_3=new Requerimiento_3();

                requerimiento_3.setCargo(derivado.getString("Cargo"));
                requerimiento_3.setMAXSalario(derivado.getInt("MAX(l.Salario)"));
                
                

                respuesta.add(requerimiento_3);
            }

            statement.close();
            derivado.close();
        } catch (SQLException e) {
            System.err.println("Error consulta del dato del tercer requerimiento invalida");
            e.printStackTrace();

        } finally {
            if(conexion!=null)
                conexion.close();

        }

        // Retornar la colección de vo's
        return respuesta;

    }
}