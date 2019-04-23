package com.edu.biblia;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    String pattern = "(.+) (\\d+):(\\d+)";
    int versiculoActual = 0;
    Pattern r = Pattern.compile(pattern);
    @Autowired
    private VersiculoRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String fileName = "C:\\Users\\Edu\\IdeaProjects\\biblia\\src\\main\\resources\\versiculos.txt";

        StringBuilder s = new StringBuilder();
        s.append("<html><head><title></title><style>" +
                "td{" +
                "   border:1px black solid;" +
                "   width:200px;" +
                "}</style></head><body><table>");
        int columnas = 4;
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {

            stream.forEach(line -> {
                try {
                    VersiculoPlano vers_aux = parse(line);
                    Versiculo versiculo = repo.findByLibroLibroAndCapituloAndVersiculo(vers_aux.getLibro(), vers_aux.getCapitulo(), vers_aux.getVersiculo());
                    if(versiculoActual%columnas==0){
                        s.append("<tr><td>").append(versiculo).append("</td>");
                    }else if(versiculoActual%columnas==columnas-1){
                        s.append("<td>").append(versiculo).append("</td></tr>");
                    }else{
                        s.append("<td>").append(versiculo).append("</td>");
                    }
                    versiculoActual += 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            if(versiculoActual%columnas!=columnas-1){
                s.append("</tr>");
            }
            s.append("</table></body></html>");
            Files.write(Paths.get("C:\\Users\\Edu\\IdeaProjects\\biblia\\src\\main\\resources\\output.html"), s.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private VersiculoPlano parse(String entrada) throws Exception {
        // Now create matcher object.
        Matcher m = r.matcher(entrada);

        if (m.find( )) {
            return new VersiculoPlano(m.group(1), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)));
        } else {
            throw new Exception("Versículo " + entrada + " mal escrito!");
        }
    }
}
