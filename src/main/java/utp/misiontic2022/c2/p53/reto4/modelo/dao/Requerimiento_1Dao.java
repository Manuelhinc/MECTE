package utp.misiontic2022.c2.p53.reto4.modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utp.misiontic2022.c2.p53.reto4.modelo.vo.Requerimiento_1;
import utp.misiontic2022.c2.p53.reto4.util.JDBCUtilities;

public class Requerimiento_1Dao {
    public ArrayList<Requerimiento_1> requerimiento1() throws SQLException {
        // Su código
        ArrayList<Requerimiento_1> respuesta = new ArrayList<Requerimiento_1>();
        Connection conexion = JDBCUtilities.getConnection();
        String consulta="select Constructora, Ciudad, Banco_Vinculado, Porcentaje_Cuota_Inicial, Clasificacion, Fecha_Inicio from Proyecto P where Ciudad =='Pereira'";
        try {

            // Recorrer los registros en los VO específicos
            Statement statement=conexion.createStatement();
            ResultSet derivado=statement.executeQuery(consulta);
            while(derivado.next()){
                Requerimiento_1 requerimiento_1=new Requerimiento_1();

                requerimiento_1.setConstructora(derivado.getString("Constructora"));
                requerimiento_1.setCiudad(derivado.getString("Ciudad"));
                requerimiento_1.setBanco(derivado.getString("Banco_Vinculado"));
                requerimiento_1.setPorcentaje(derivado.getDouble("Porcentaje_Cuota_Inicial"));
                requerimiento_1.setClasificacion(derivado.getString("Clasificacion"));
                requerimiento_1.setFecha(derivado.getString("Fecha_Inicio"));

                respuesta.add(requerimiento_1);
            }

            statement.close();
            derivado.close();

        } catch (SQLException e) {
            System.err.println("Error consulta del dato del primer requerimiento invalida");
            e.printStackTrace();

        } finally {
            if(conexion!=null)
                conexion.close();

        }

        // Retornar la colección de vo's
        return respuesta;

    }
}