    
        $usu = $_POST['UserEmail'];
        $contra = $_POST['Password'];
    if (!$enlace = mysql_connect('localhost', 'root', '')) {
        echo 'No pudo conectarse a mysql';
    } else {
        $bd_seleccionada = mysql_select_db('red', $enlace);
        if (!$bd_seleccionada) {
                //die('No encuentra la base de datos');
        }else{
            $sql = "SELECT nombre,apellidos,edad,fecha,correo,pais,ciudad,usuario,nombreArtista,generos,instrumentos,paginaWebAr,recorridoProfe,nombreBar,generosBar,PaginaWebLocal,Rol FROM clientes WHERE usuario = '$usu' AND contrasena = '$contra'";
            $resultado = mysql_query($sql, $enlace);
            if (mysql_num_rows($resultado)==0) {
                echo "retrasado";
            }else{
                echo "invecil";
                $response = array();
                $response["products"] = array();
                $row = mysql_fetch_row($resultado);
                $product = array();
                $product["nombre"] = $resultado[0];
                $product["ape"] = $resultado[1];
                $product["edad"] = $resultado[2];
                $product["fecha"] = $resultado[3];
                $product["correo"] = $resultado[4];
                $product["pais"] = $resultado[5];
                $product["ciudad"] = $resultado[6];
                $product["usuario"] = $resultado[7];
                $product["nombreArtista"] = $resultado[8];
                $product["generos"] = $resultado[9];
                $product["instru"] = $resultado[10];
                $product["paginaWeb"] = $resultado[11];
                $product["recorrido"] = $resultado[12];
                $product["nombreBar"] = $resultado[13];
                $product["generosBar"] = $resultado[14];
                $product["pagWebBar"] = $resultado[15];
                $product["rol"] = $resultado[16];
                array_push($response["products"], $product);
                echo json_encode($response);

            }
        }
    }