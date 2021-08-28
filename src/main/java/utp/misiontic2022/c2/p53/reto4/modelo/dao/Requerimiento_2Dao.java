package utp.misiontic2022.c2.p53.reto4.modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utp.misiontic2022.c2.p53.reto4.modelo.vo.Requerimiento_2;
import utp.misiontic2022.c2.p53.reto4.util.JDBCUtilities;

public class Requerimiento_2Dao {
    public ArrayList<Requerimiento_2> requerimiento2() throws SQLException {
        // Su código
        ArrayList<Requerimiento_2> respuesta = new ArrayList<Requerimiento_2>();
        Connection conexion = JDBCUtilities.getConnection();
        String consulta="SELECT c.Proveedor, p.Constructora, c.Pagado\n"+
        "from Compra c \n"+
        "inner join Proyecto p \n"+
        "ON c.ID_Proyecto = p.ID_Proyecto\n"+
        "WHERE c.Proveedor IN ('Homecenter','JUMBO')\n"+
        "AND c.Pagado ='Si' \n"+
        "and p.Constructora LIKE  '%S.A.'\n"+
        "ORDER BY c.Proveedor";

        try {
            // Recorrer los registros en los VO específicos
            Statement statement=conexion.createStatement();
            ResultSet derivado=statement.executeQuery(consulta);
            while(derivado.next()){
                Requerimiento_2 requerimiento_2=new Requerimiento_2();

                requerimiento_2.setProveedor(derivado.getString("Proveedor"));
                requerimiento_2.setConstructora(derivado.getString("Constructora"));
                requerimiento_2.setPagado(derivado.getString("Pagado"));
                

                respuesta.add(requerimiento_2);
            }

            statement.close();
            derivado.close();

        } catch (SQLException e) {
            System.err.println("Error consulta del dato del segundo requerimiento invalida");
            e.printStackTrace();

        } finally {
            if(conexion!=null)
                conexion.close();

        }

        // Retornar la colección de vo's
        return respuesta;

    }
}