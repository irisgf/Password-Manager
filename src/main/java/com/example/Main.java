package com.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

//para poder utilizar esta app de escritorio será necesario tener una base de datos

public class Main {
   private static final String BASE_URL = "TU_URL"; 

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);

      while (true) {
         System.out.println("\n=== ADMINISTRADOR ===");
         System.out.println("1. Ver usuarios");
         System.out.println("2. Modificar contraseña");
         System.out.println("3. Salir");
         System.out.print("Selecciona una opción: ");
         int opcion = scanner.nextInt();
         scanner.nextLine(); 

         switch (opcion) {
            case 1:
               listarUsuarios();
               break;
            case 2:
               modificarContrasena(scanner);
               break;
            case 3:
               System.out.println("¡Adiós!");
               scanner.close();
               return;
            default:
               System.out.println("Opción inválida.");
         }
      }
   }

   public static void listarUsuarios() {
      try {
          HttpClient client = HttpClient.newHttpClient();
          HttpRequest request = HttpRequest.newBuilder()
                  .uri(URI.create(BASE_URL + "/api/usuarios")) 
                  .GET()
                  .build();
  
          HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
  
          if (response.statusCode() != 200) {
              System.err.println("Error: Código de respuesta HTTP " + response.statusCode());
              return;
          }
  
          JSONArray usuarios = new JSONArray(response.body());
  
          System.out.println("\nUsuarios:");
          for (int i = 0; i < usuarios.length(); i++) {
              JSONObject usuario = usuarios.getJSONObject(i);
              System.out.println("Nombre: " + usuario.getString("nombre"));
          }
      } catch (Exception e) {
          System.err.println("Error al listar usuarios: " + e.getMessage());
          e.printStackTrace();
      }
  }  

   private static void modificarContrasena(Scanner scanner) {
      try {
         System.out.print("Nombre del usuario: ");
         String username = scanner.nextLine();  
         System.out.print("Nueva contraseña: ");
         String nuevaContrasena = scanner.nextLine();

         String cuerpoJson = "{\"contrasena\":\"" + nuevaContrasena + "\"}";

         HttpRequest request = HttpRequest.newBuilder()
                 .uri(URI.create(BASE_URL + "/api/usuarios/" + username + "/contrasena"))
                 .header("Content-Type", "application/json")
                 .PUT(BodyPublishers.ofString(cuerpoJson))
                 .build();

         HttpClient client = HttpClient.newHttpClient();
         HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

         if (response.statusCode() == 200) {
            System.out.println("Contraseña actualizada con éxito.");
         } else {
            System.out.println("Error al actualizar contraseña. Código de estado: " + response.statusCode());
            System.out.println("Respuesta del servidor: " + response.body());
         }
      } catch (Exception e) {
         System.out.println("Error al modificar contraseña: " + e.getMessage());
         e.printStackTrace();
      }
   }
}
