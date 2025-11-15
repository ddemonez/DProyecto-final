package reportes;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import conexion.conexion;
import com.itextpdf.text.pdf.draw.LineSeparator;


public class GenerarReportePDF {

    public static void crearReporte() {
        Document documento = new Document(PageSize.A4);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String fecha = sdf.format(new Date());
        String nombreArchivo = "Reporte_Reparaciones_" + fecha + ".pdf";

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // === Encabezado con logotipo ===
            try {
                // Ruta del logotipo (debe estar en src/imagenes/logo.png)
                Image logo = Image.getInstance("src/imagenes/logo.png");
                logo.scaleToFit(80, 80);
                logo.setAlignment(Element.ALIGN_LEFT);
                documento.add(logo);
            } catch (Exception ex) {
                System.out.println("No se encontró el logotipo. Se omitirá la imagen.");
            }

            // === Título ===
            Paragraph titulo = new Paragraph("MOTO REPARACIONES CALEL\n\n",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, BaseColor.BLUE));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            // === Subtítulo ===
            Paragraph subtitulo = new Paragraph("Listado de Servicios Realizados\n\n",
                    FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY));
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subtitulo);

            // === Línea separadora ===
            LineSeparator linea = new LineSeparator();
            linea.setLineColor(BaseColor.LIGHT_GRAY);
            documento.add(linea);
            documento.add(new Paragraph("\n"));

            // === Crear tabla ===
            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{1.5f, 3, 2.5f, 3, 2, 2});

            // Encabezados
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
            BaseColor azulOscuro = new BaseColor(0, 102, 204);

            String[] headers = {"Código", "Cliente", "Modelo", "Servicio", "Costo", "Fecha"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, fontHeader));
                cell.setBackgroundColor(azulOscuro);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(6);
                tabla.addCell(cell);
            }

            // === Conexión a base de datos ===
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reparaciones");

            // === Rellenar tabla con datos ===
            Font fontData = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
            while (rs.next()) {
                tabla.addCell(new Phrase(String.valueOf(rs.getInt("codigo_servicio")), fontData));
                tabla.addCell(new Phrase(rs.getString("nombre_cliente"), fontData));
                tabla.addCell(new Phrase(rs.getString("modelo_moto"), fontData));
                tabla.addCell(new Phrase(rs.getString("tipo_servicio"), fontData));
                tabla.addCell(new Phrase(String.format("Q %.2f", rs.getDouble("costo")), fontData));
                tabla.addCell(new Phrase(rs.getString("fecha_ingreso"), fontData));
            }

            documento.add(tabla);

            // === Espacio final ===
            documento.add(new Paragraph("\n"));

            // === Pie de página ===
            Paragraph pie = new Paragraph("Generado automáticamente por Moto Reparaciones Calel\n" +
                    "Fecha de generación: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()),
                    FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, BaseColor.GRAY));
            pie.setAlignment(Element.ALIGN_CENTER);
            documento.add(pie);

            documento.close();
            con.close();

            JOptionPane.showMessageDialog(null,
                    "Reporte PDF generado correctamente:\n" + nombreArchivo,
                    "Reporte creado", JOptionPane.INFORMATION_MESSAGE);

            // Abrir el archivo automáticamente
            File pdf = new File(nombreArchivo);
            if (pdf.exists()) {
                java.awt.Desktop.getDesktop().open(pdf);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al generar el reporte:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
