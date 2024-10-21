import java.sql.*;
import java.util.Scanner;

public class Metodos {

    /**
     * Establece una conexión con la base de datos PostgreSQL.
     *
     * @return Connection objeto que representa la conexión a la base de datos.
     */
    public static Connection conexion() {
        Connection conn = null;
        try {
            String driver = "jdbc:postgresql:";
            String host = "//localhost:";
            String porto = "5432";
            String sid = "postgres";
            String usuario = "postgres";
            String password = "postgres";
            String url = driver + host + porto + "/" + sid;

            conn = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión establecida correctamente.");
        } catch (SQLException e) {
            System.err.println("Erro na conexión: " + e.getMessage());
        }
        return conn;
    }

    /**
     * Lista todos los productos en la base de datos.
     *
     * Este metodo utiliza un ResultSet de tipo scrollable y updatable
     * para obtener y mostrar el contenido de la tabla 'produtos'.
     */
    //Metodo para listar contenido del ResultSet
    public static void listarProdutos() {
        String sql = "SELECT produtos.* FROM produtos";
        try (Connection conn = conexion();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Código: " + rs.getString("codigo"));
                System.out.println("Descripción: " + rs.getString("descricion"));
                System.out.println("Prezo: " + rs.getInt("prezo"));
                System.out.println("Data de Caducidade: " + rs.getDate("datac"));
                System.out.println("------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    /**
     * Actualiza el precio de un producto en la base de datos.
     *
     * Este metodo solicita al usuario el código del producto a actualizar
     * y el nuevo precio. Utiliza un ResultSet para realizar la actualización.
     */
    //Metodo para actualizar el precio del producto p2 a 8 desde dentro del ResultSet
    public static void actualizarProduto() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce o código do produto a actualizar: ");
        String codigo = sc.nextLine();
        System.out.print("Introduce o novo prezo: ");
        int prezo = sc.nextInt();

        String sql = "SELECT produtos.* FROM produtos WHERE codigo = '" + codigo + "'";
        try (Connection conn = conexion();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                rs.updateInt("prezo", prezo); // Actualizamos el precio proporcionado por el usuario
                rs.updateRow(); // Confirmaos la actualizacion
                System.out.println("Produto " + codigo + " actualizado correctamente.");
            } else {
                System.out.println("Produto non atopado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao actualizar produto: " + e.getMessage());
        }
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * Este metodo solicita al usuario los detalles del nuevo producto
     * y lo inserta en la tabla 'produtos' usando un ResultSet.
     */
    //Metodo para insertar el producto p4 (martelo, 20) desde dentro del ResultSet
    public static void inserirProduto() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce o código do novo produto: ");
        String codigo = sc.nextLine();
        System.out.print("Introduce a descrición do novo produto: ");
        String descricion = sc.nextLine();
        System.out.print("Introduce o prezo do novo produto: ");
        int prezo = sc.nextInt();
        sc.nextLine(); // Limpiar buffer de Scanner
        System.out.print("Introduce a data de caducidade (YYYY-MM-DD): ");
        String data = sc.nextLine();

        String sql = "SELECT produtos.* FROM produtos";
        try (Connection conn = conexion();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {

            rs.moveToInsertRow(); // Movemos el cursor a la fila de inserción
            rs.updateString("codigo", codigo);
            rs.updateString("descricion", descricion);
            rs.updateInt("prezo", prezo);
            rs.updateDate("datac", Date.valueOf(data)); // Convertimos la fecha proporcionada
            rs.insertRow(); // Insertamos la fila
            System.out.println("Produto " + codigo + " inserido correctamente.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
        }
    }


    /**
     * Borra un producto de la base de datos.
     *
     * Este metodo solicita al usuario el código del producto a eliminar
     * y lo elimina de la tabla 'produtos' usando un ResultSet.
     */
    //Metodo para borrar el producto p3 desde dentro del ResultSet
    public static void borrarProduto() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce o código do produto a eliminar: ");
        String codigo = sc.nextLine();

        String sql = "SELECT produtos.* FROM produtos WHERE codigo = '" + codigo + "'";
        try (Connection conn = conexion();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                rs.deleteRow(); // Borramos la fila actual
                System.out.println("Produto " + codigo + " eliminado correctamente.");
            } else {
                System.out.println("Produto non atopado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao borrar produto: " + e.getMessage());
        }
    }
}
