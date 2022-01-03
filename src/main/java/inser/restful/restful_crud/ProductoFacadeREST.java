/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inser.restful.restful_crud;

import inser.persistence.restful_crud.LinkedList_envuelta;
import static inser.persistence.restful_crud.PersistenceConfig.jdbc_contraseña;
import static inser.persistence.restful_crud.PersistenceConfig.jdbc_usuario;
import inser.persistence.restful_crud.Producto;
import static inser.persistence.restful_crud.Producto.SELECT_findAll_order;
import static inser.persistence.restful_crud.Producto.SELECT_findByCodigoProducto_order;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import java.util.LinkedList;

/**
 *
 * @author informatica
 */
@jakarta.ejb.Stateless
@Path("inser.restful.restful_crud.producto")
public class ProductoFacadeREST extends AbstractFacade<Producto> {
    @Context
    // opción 1: private UriInfo context;
    // opción 2: 
    private HttpServletRequest context;
    
//    @PersistenceContext(unitName = "restful_crudPU")
    public EntityManager entityManager;
    public Map<String, String> propiedades_mapa = new HashMap();

    public ProductoFacadeREST() {
        super(Producto.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Producto entity) {
        String texto;     
        texto = context.getHeader(jdbc_usuario);
        propiedades_mapa.put(jdbc_usuario, texto);
        texto = context.getHeader(jdbc_contraseña);        
        propiedades_mapa.put(jdbc_contraseña, texto);
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Producto entity) {
        String texto;     
        texto = context.getHeader(jdbc_usuario);
        propiedades_mapa.put(jdbc_usuario, texto);
        texto = context.getHeader(jdbc_contraseña);        
        propiedades_mapa.put(jdbc_contraseña, texto);
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        String texto;     
        texto = context.getHeader(jdbc_usuario);
        propiedades_mapa.put(jdbc_usuario, texto);
        texto = context.getHeader(jdbc_contraseña);        
        propiedades_mapa.put(jdbc_contraseña, texto);
        Producto producto = super.find(id);
        super.remove(producto);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Producto find(@PathParam("id") String id) {
        String texto;     
        texto = context.getHeader(jdbc_usuario);
        propiedades_mapa.put(jdbc_usuario, texto);
        texto = context.getHeader(jdbc_contraseña);        
        propiedades_mapa.put(jdbc_contraseña, texto);
        Producto producto = super.find(id);
        return producto;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LinkedList_envuelta<Producto> findAll_envuelta() {
        LinkedList_envuelta<Producto> linkedList_envuelta = new LinkedList_envuelta();
        String texto;     
        texto = context.getHeader(jdbc_usuario);
        propiedades_mapa.put(jdbc_usuario, texto);
        texto = context.getHeader(jdbc_contraseña);        
        propiedades_mapa.put(jdbc_contraseña, texto);
        List<Producto> productos_lista = new LinkedList<Producto>(super.findAll());
        linkedList_envuelta.lista = new LinkedList(productos_lista);
        return linkedList_envuelta;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LinkedList_envuelta<Producto> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        LinkedList_envuelta<Producto> linkedList_envuelta = new LinkedList_envuelta();
        String texto;     
        texto = context.getHeader(jdbc_usuario);
        propiedades_mapa.put(jdbc_usuario, texto);
        texto = context.getHeader(jdbc_contraseña);        
        propiedades_mapa.put(jdbc_contraseña, texto);
        List<Producto> productos_lista = super.findRange(new int[]{from, to});
        linkedList_envuelta.lista = new LinkedList(productos_lista);
        return linkedList_envuelta;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        String texto;     
        texto = context.getHeader(jdbc_usuario);
        propiedades_mapa.put(jdbc_usuario, texto);
        texto = context.getHeader(jdbc_contraseña);        
        propiedades_mapa.put(jdbc_contraseña, texto);
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        String usuario;
        String contraseña;
        String [] error = { "" };
        usuario = propiedades_mapa.get(jdbc_usuario);
        contraseña = propiedades_mapa.get(jdbc_contraseña);
        entityManager = ApplicationConfig.getEntityManager("restful_crudPU"
                , usuario, contraseña, propiedades_mapa, error);
        return entityManager;
    }

    @GET
    @Path("{from}/{to}/{descripcion}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LinkedList_envuelta<Producto> findLike_descripcion(@PathParam("from") Integer from, @PathParam("to") Integer to, @PathParam("descripcion") String descripcion) {
        LinkedList_envuelta<Producto> linkedList_envuelta = new LinkedList_envuelta();
        List<Producto> productos_lista;
        String texto;     
        texto = context.getHeader(jdbc_usuario);
        propiedades_mapa.put(jdbc_usuario, texto);
        texto = context.getHeader(jdbc_contraseña);        
        propiedades_mapa.put(jdbc_contraseña, texto);
        TypedQuery<Producto> typedQuery = getEntityManager().createNamedQuery("Producto.findByCodigoProducto", Producto.class);
        typedQuery = typedQuery.setParameter("descripcion", descripcion);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        productos_lista = typedQuery.getResultList();
        linkedList_envuelta.lista = new LinkedList(productos_lista);
        return linkedList_envuelta;
    }
    
    @GET
    @Path("{from}/{to}/{campo_ordenacion}/{asc}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LinkedList_envuelta<Producto> find_orden(@PathParam("from") Integer from
            , @PathParam("to") Integer to
            , @PathParam("campo_ordenacion") String campo_ordenacion
            , @PathParam("asc") String asc) {
        List<Producto> productos_lista;
        LinkedList_envuelta<Producto> linkedList_envuelta = new LinkedList_envuelta();
        String texto;     
        texto = context.getHeader(jdbc_usuario);
        propiedades_mapa.put(jdbc_usuario, texto);
        texto = context.getHeader(jdbc_contraseña);        
        propiedades_mapa.put(jdbc_contraseña, texto);
        String consulta = SELECT_findAll_order
                + campo_ordenacion;
        if (asc.toLowerCase().equals("desc")) {
            consulta = consulta + " DESC";
        }
        TypedQuery<Producto> typedQuery = getEntityManager().createQuery(consulta, Producto.class);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        productos_lista = typedQuery.getResultList();
        linkedList_envuelta.lista = new LinkedList(productos_lista);
        return linkedList_envuelta;
    }

    @GET
    @Path("{from}/{to}/{descripcion}/{campo_ordenacion}/{asc}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LinkedList_envuelta<Producto> findLike_descripcion_orden(@PathParam("from") Integer from
            , @PathParam("to") Integer to
            , @PathParam("descripcion") String descripcion
            , @PathParam("campo_ordenacion") String campo_ordenacion
            , @PathParam("asc") String asc) {
        LinkedList_envuelta<Producto> linkedList_envuelta = new LinkedList_envuelta();
        List<Producto> productos_lista;
        String texto;     
        texto = context.getHeader(jdbc_usuario);
        propiedades_mapa.put(jdbc_usuario, texto);
        texto = context.getHeader(jdbc_contraseña);        
        propiedades_mapa.put(jdbc_contraseña, texto);
        String consulta = SELECT_findByCodigoProducto_order
                + campo_ordenacion;
        if (asc.toLowerCase().equals("desc")) {
            consulta = consulta + " DESC";
        }
        TypedQuery<Producto> typedQuery = getEntityManager().createQuery(consulta, Producto.class);
        typedQuery = typedQuery.setParameter("descripcion", descripcion);
        typedQuery = typedQuery.setMaxResults(to - from + 1);
        typedQuery = typedQuery.setFirstResult(from);
        productos_lista = typedQuery.getResultList();
        linkedList_envuelta.lista = new LinkedList(productos_lista);
        return linkedList_envuelta;
    }
    
}
