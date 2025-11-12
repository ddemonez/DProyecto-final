package modelo;

public class Reparacion {/// encapsulamiento de los datos 
    private int codigo_servicio;
    private String nombre_cliente;
    private String modelo_moto;
    private String tipo_servicio;
    private double costo;
    private String fecha_ingreso;

    public Reparacion() {}

    public Reparacion(int codigo_servicio, String nombre_cliente, String modelo_moto,
                      String tipo_servicio, double costo, String fecha_ingreso) {
        this.codigo_servicio = codigo_servicio;
        this.nombre_cliente = nombre_cliente;
        this.modelo_moto = modelo_moto;
        this.tipo_servicio = tipo_servicio;
        this.costo = costo;
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getCodigo_servicio() { return codigo_servicio; }
    public void setCodigo_servicio(int codigo_servicio) { this.codigo_servicio = codigo_servicio; }

    public String getNombre_cliente() { return nombre_cliente; }
    public void setNombre_cliente(String nombre_cliente) { this.nombre_cliente = nombre_cliente; }

    public String getModelo_moto() { return modelo_moto; }
    public void setModelo_moto(String modelo_moto) { this.modelo_moto = modelo_moto; }

    public String getTipo_servicio() { return tipo_servicio; }
    public void setTipo_servicio(String tipo_servicio) { this.tipo_servicio = tipo_servicio; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public String getFecha_ingreso() { return fecha_ingreso; }
    public void setFecha_ingreso(String fecha_ingreso) { this.fecha_ingreso = fecha_ingreso; }
}
