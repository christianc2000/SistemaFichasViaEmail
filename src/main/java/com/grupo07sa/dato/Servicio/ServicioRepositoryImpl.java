/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo07sa.dato.Servicio;

import com.grupo07sa.client.ClientDB;
import com.grupo07sa.dato.CommandDTO;
import com.grupo07sa.dato.Servicio.dto.SalaDTO;
import com.grupo07sa.dato.Servicio.dto.ServicioDTO;
import com.grupo07sa.dato.Servicio.dto.TipoServicioDTO;
import com.grupo07sa.help.Lexer;
import com.grupo07sa.help.MyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class ServicioRepositoryImpl implements ServicioRepository {

    private ClientDB db;

    public ServicioRepositoryImpl() {
        this.db = new ClientDB();
    }

    @Override
    public List<ServicioDTO> getAllservicios() {
        Connection conn = null;
        List<ServicioDTO> servicioList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT servicios.*, tipo_servicios.nombre as tipo_servicio_nombre, salas.nombre as sala_nombre FROM servicios,tipo_servicios,salas\n"
                    + "WHERE salas.id=servicios.sala_id and tipo_servicios.id=servicios.tipo_servicio_id ORDER BY servicios.id ASC;";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ServicioDTO servicio = new ServicioDTO();
                servicio.setId(rs.getInt("id"));
                servicio.setNombre(rs.getString("nombre"));
                servicio.setFoto(rs.getString("foto"));
                servicio.setCosto(rs.getFloat("costo"));
                servicio.setTipo_servicio_id(rs.getInt("tipo_servicio_id"));
                servicio.setSala_id(rs.getInt("sala_id"));
                servicio.setSala(new SalaDTO(servicio.getSala_id(), rs.getString("sala_nombre")));
                servicio.setTipo_servicio(new TipoServicioDTO(servicio.getTipo_servicio_id(), rs.getString("tipo_servicio_nombre")));
                servicio.setCreated_at(rs.getTimestamp("created_at"));
                servicio.setUpdated_at(rs.getTimestamp("updated_at"));
                servicioList.add(servicio);
            }

        } catch (SQLException e) {
            // Translate the SQL error into a custom exception
            int errorCode = e.getErrorCode(); // Implement this method to map SQL error codes
            throw new MyException("Error al obtener los servicios de la base de datos", errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
        System.out.println("cantidad de registro de servicios: " + servicioList.size());
        return servicioList;
    }

    @Override
    public List<String[]> getAtrServicios(
            String[] atributos, String atrs) {
        Connection conn = null;
        List<String[]> resultList = new ArrayList<>();

        try {
            conn = db.establecerConexion();
            String sql = "SELECT " + atrs + " FROM servicios";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] valores = new String[atributos.length];
                for (int i = 0; i < atributos.length; i++) {
                    valores[i] = rs.getString(atributos[i]);
                }
                resultList.add(valores);
            }
        } catch (SQLException e) {
            // Maneja la excepción aquí (puedes loguearla o tomar otras acciones)
            int errorCode = e.getErrorCode(); // Implementa la lógica para mapear códigos de error
            System.err.println("Error al obtener usuarios de la base de datos (Código de error: " + errorCode + ")");
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }

        return resultList;
    }

    @Override
    public ServicioDTO createServicio(ServicioDTO servicioCreate) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "INSERT INTO servicios (nombre,foto,costo,sala_id,tipo_servicio_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Configura los valores de los parámetros
            stmt.setString(1, servicioCreate.getNombre());
            stmt.setString(2, servicioCreate.getFoto());
            stmt.setFloat(3, servicioCreate.getCosto());
            stmt.setInt(4, servicioCreate.getSala_id());
            stmt.setInt(5, servicioCreate.getTipo_servicio_id());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Ahora buscar el usuario con el ID más alto
                String findSql = "SELECT servicios.*, tipo_servicios.nombre as tipo_servicio_nombre, salas.nombre as sala_nombre FROM servicios,tipo_servicios,salas "
                        + "WHERE salas.id=servicios.sala_id and tipo_servicios.id=servicios.tipo_servicio_id ORDER BY servicios.id DESC LIMIT 1";
                PreparedStatement findStmt = conn.prepareStatement(findSql);
                ResultSet rs = findStmt.executeQuery();
                if (rs.next()) {
                    // Construir y retornar el objeto UserDTO
                    ServicioDTO servicio = new ServicioDTO();
                    servicio.setId(rs.getInt("id"));
                    servicio.setNombre(rs.getString("nombre"));
                    servicio.setFoto(rs.getString("foto"));
                    servicio.setCosto(rs.getFloat("costo"));
                    servicio.setTipo_servicio_id(rs.getInt("tipo_servicio_id"));
                    servicio.setSala_id(rs.getInt("sala_id"));
                    servicio.setSala(new SalaDTO(servicio.getSala_id(), rs.getString("sala_nombre")));
                    servicio.setTipo_servicio(new TipoServicioDTO(servicio.getTipo_servicio_id(), rs.getString("tipo_servicio_nombre")));
                    servicio.setCreated_at(rs.getTimestamp("created_at"));
                    servicio.setUpdated_at(rs.getTimestamp("updated_at"));
                    return servicio;
                } else {
                    throw new MyException("Failed to retrieve the new user", 0);
                }
            } else {
                throw new MyException("No rows affected", 0);
            }
        } catch (SQLException e) {
            int errorCode = e.getErrorCode();
            throw new MyException("" + e, errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public ServicioDTO findServicio(int id) {
        Connection conn = null;
        ServicioDTO servicio = null;
        try {
            conn = db.establecerConexion();
            String sql = "SELECT servicios.*, tipo_servicios.nombre as tipo_servicio_nombre, salas.nombre as sala_nombre FROM servicios,tipo_servicios,salas WHERE servicios.id = ? and servicios.tipo_servicio_id=tipo_servicios.id and servicios.sala_id=salas.id;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                servicio = new ServicioDTO();
                servicio.setId(rs.getInt("id"));
                servicio.setNombre(rs.getString("nombre"));
                servicio.setFoto(rs.getString("foto"));
                servicio.setCosto(rs.getFloat("costo"));
                servicio.setTipo_servicio_id(rs.getInt("tipo_servicio_id"));
                servicio.setTipo_servicio(new TipoServicioDTO(servicio.getId(), rs.getString("tipo_servicio_nombre")));
                servicio.setSala_id(rs.getInt("sala_id"));
                servicio.setSala(new SalaDTO(servicio.getSala_id(), rs.getString("sala_nombre")));
                servicio.setCreated_at(rs.getTimestamp("created_at"));
                servicio.setUpdated_at(rs.getTimestamp("updated_at"));
            } else {
                throw new MyException("Servicio no encontrado con el ID: " + id, 0);
            }
            return servicio;
        } catch (SQLException e) {
            throw new MyException("Error al buscar el servicio: " + e.getMessage(), e.getErrorCode());
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public ServicioDTO updateServicio(ServicioDTO servicioUpdated) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "UPDATE servicios SET nombre = ?, foto = ?, costo = ?, sala_id = ?, tipo_servicio_id = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Configura los valores de los parámetros
                stmt.setString(1, servicioUpdated.getNombre());
                stmt.setString(2, servicioUpdated.getFoto());
                stmt.setFloat(3, servicioUpdated.getCosto());
                stmt.setInt(4, servicioUpdated.getSala_id());
                stmt.setInt(5, servicioUpdated.getTipo_servicio_id());
                stmt.setInt(6, servicioUpdated.getId());
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    String sqlFind = "SELECT servicios.*, tipo_servicios.nombre as tipo_servicio_nombre, salas.nombre as sala_nombre "
                            + "FROM servicios "
                            + "JOIN tipo_servicios ON servicios.tipo_servicio_id = tipo_servicios.id "
                            + "JOIN salas ON servicios.sala_id = salas.id "
                            + "WHERE servicios.id = ?";
                    try (PreparedStatement stmtFind = conn.prepareStatement(sqlFind)) {
                        stmtFind.setInt(1, servicioUpdated.getId());
                        try (ResultSet rs = stmtFind.executeQuery()) {
                            if (rs.next()) {
                                servicioUpdated.setId(rs.getInt("id"));
                                servicioUpdated.setNombre(rs.getString("nombre"));
                                servicioUpdated.setFoto(rs.getString("foto"));
                                servicioUpdated.setCosto(rs.getFloat("costo"));
                                servicioUpdated.setTipo_servicio_id(rs.getInt("tipo_servicio_id"));
                                servicioUpdated.setTipo_servicio(new TipoServicioDTO(servicioUpdated.getId(), rs.getString("tipo_servicio_nombre")));
                                servicioUpdated.setSala_id(rs.getInt("sala_id"));
                                servicioUpdated.setSala(new SalaDTO(servicioUpdated.getSala_id(), rs.getString("sala_nombre")));
                                servicioUpdated.setCreated_at(rs.getTimestamp("created_at"));
                                servicioUpdated.setUpdated_at(rs.getTimestamp("updated_at"));
                                return servicioUpdated;
                            } else {
                                throw new MyException("Fallo al actualizar y mostrar el servicio", 0);
                            }
                        }
                    }
                } else {
                    throw new MyException("Fallo al actualizar el servicio", 0);
                }
            }
        } catch (SQLException e) {
            int errorCode = e.getErrorCode();
            throw new MyException("" + e, errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    @Override
    public boolean deleteServicioById(int id) {
        Connection conn = null;
        try {
            conn = db.establecerConexion();
            String sql = "DELETE FROM servicios WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            int errorCode = e.getErrorCode();
            throw new MyException("Error al eliminar el servicio: " + e.getMessage(), errorCode);
        } finally {
            if (conn != null) {
                db.cerrarConexion();
            }
        }
    }

    public String ListaToString(List<ServicioDTO> data) {
        String text = "";
        text = "id| nombre | foto | costo | id tipo de servicio | tipo de servicio | Sala | Fecha de creación | Fecha de actualización";
        for (int i = 0; i < data.size(); i++) {
            text = text + data.get(i).getId() + " | ";
            text = text + data.get(i).getNombre() + " | ";
            text = text + data.get(i).getFoto() + " | ";
            text = text + data.get(i).getCosto() + " | ";
            text = text + data.get(i).getTipo_servicio_id() + " | ";
            text = text + data.get(i).getTipo_servicio().getNombre() + " | ";
            text = text + data.get(i).getSala().getNombre() + " | ";
            text = text + data.get(i).getCreated_at() + " | ";
            text = text + data.get(i).getUpdated_at();
            text = text + "\n";
        }
        return text;
    }

    public String MatrizToString(String[][] matriz) {
        String texto = "";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                texto = texto + matriz[i][j] + " | ";
            }
            texto = texto + "\n";
        }
        return texto;
    }

    public String MostrarServicio(List<String[]> data, String atr[]) {
        String text = "";
        for (int i = 0; i < atr.length; i++) {
            text = text + atr[i] + " | ";
        }
        text = text + "\n";
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length; j++) {
                text = text + data.get(i)[j] + " | ";
            }
            text = text + "\n";
        }
        return text;
    }

    public static void main(String[] args) {
        ServicioRepositoryImpl service = new ServicioRepositoryImpl();
        //Listar servicios
        /*List<ServicioDTO> serviceList = service.getAllservicios();
        String datos = service.ListaToString(serviceList);
        System.out.println(datos);*/
        //Listar servicios por atributo
        Lexer sintaxis = new Lexer();
//         CommandDTO comando = sintaxis.analizarMensaje("LIST[servicios];");
//          CommandDTO comando = sintaxis.analizarMensaje("LIST[servicios:id,nombre,costo];");
        // CommandDTO comando = sintaxis.analizarMensaje("SHOW[servicios:id=1];");
        //CommandDTO comando = sintaxis.analizarMensaje("UPDATE[servicios:id=16,nombre=Servicio Tecno parcial 2];");
        CommandDTO comando = sintaxis.analizarMensaje("DELETE[servicios:id=16];");
        /*String atributos = comando.getAttributesNameToString();
        String[] atr = comando.getAttributesName();
        List<String[]> serviceAtr = service.getAtrServicios(atr, atributos);*/
        String[][] atrval = comando.getAttributesValue();
        ServicioDTO servicio = new ServicioDTO();
        for (int i = 0; i < atrval.length; i++) {
            servicio.setAttribute(atrval[i][0], atrval[i][1]);
        }
        if (servicio.getId() <= 0) {
            System.out.println("Error de colocar un valor en el id");
        }
        boolean b = service.deleteServicioById(servicio.getId());
        if (b) {
            System.out.println("Servicio eliminado exitosamente");
        } else {
            System.out.println("Error al intentar eliminar el servicio");
        }
        //ServicioDTO respuesta = service.createServicio(servicio);
        /*servicio.setNombre(servicio.getNombre() != null ? servicio.getNombre() : respuestashow.getNombre());
        servicio.setCosto(servicio.getCosto() != 0.0f ? servicio.getCosto() : respuestashow.getCosto());
        servicio.setFoto(servicio.getFoto() != null ? servicio.getFoto() : respuestashow.getFoto());
        servicio.setSala_id(servicio.getSala_id() > 0 ? servicio.getSala_id() : respuestashow.getSala_id());
        servicio.setTipo_servicio_id(servicio.getTipo_servicio_id() > 0 ? servicio.getTipo_servicio_id() : respuestashow.getTipo_servicio_id());
        ServicioDTO respuesta = service.updateServicio(servicio);
        String[][] matriz = respuesta.ServicioToMatriz();
        String datos = service.MatrizToString(matriz);
        System.out.println(datos);*/

    }

}
