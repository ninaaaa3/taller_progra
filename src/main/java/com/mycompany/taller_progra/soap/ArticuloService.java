package com.mycompany.taller_progra.soap;

import com.mycompany.taller_progra.model.Articulo;
import utilidad.ArticuloDAO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public class ArticuloService {
    private ArticuloDAO articuloDAO = new ArticuloDAO();

    @WebMethod
    public List<Articulo> obtenerTodosLosArticulos() throws Exception {
        return articuloDAO.obtenerTodosLosArticulos();
    }

    @WebMethod
    public Articulo obtenerArticuloPorId(int id) throws Exception {
        return articuloDAO.obtenerArticuloPorId(id);
    }

    @WebMethod
    public void agregarArticulo(Articulo articulo) throws Exception {
        articuloDAO.agregarArticulo(articulo);
    }

    @WebMethod
    public void actualizarArticulo(Articulo articulo) throws Exception {
        articuloDAO.actualizarArticulo(articulo);
    }

    @WebMethod
    public void eliminarArticulo(int id) throws Exception {
        articuloDAO.eliminarArticulo(id);
    }
}
