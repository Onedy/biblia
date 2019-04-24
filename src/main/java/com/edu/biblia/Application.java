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
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    //String pattern1verse = "(.+) (\\d+):(\\d+)";
    String versesInterval = "^(.+) (\\d+):(\\d+)-(\\d+)$";
    String verseEnumeration = "^(.+) (\\d+):(\\d+(,\\d+)*)$";
    String versesDelimiter = " ";
    int versiculoActual = 0;
    Pattern versesIntervalPattern = Pattern.compile(versesInterval);
    Pattern verseEnumerationPattern = Pattern.compile(verseEnumeration);
    @Autowired
    private VersiculoRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String fileName = "C:\\Users\\edoo\\IdeaProjects\\biblia\\src\\main\\resources\\versiculos.txt";

        int columnas = 4;
        StringBuilder s = new StringBuilder();
        s.append("<html><head><title></title><style>" +
                "td{" +
                "   border:1px black solid;" +
                "   width:"+(100/columnas)+"%;" +
                "}" +
                "table{" +
                "   border-spacing:1px;" +
                "}</style></head><body><table>");
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {

            stream.forEach(line -> {
                try {
                    VersiculoPlano vers_aux = parse(line);
                    List<Versiculo> versiculos = repo.findByLibroLibroAndCapituloAndVersiculoIn(vers_aux.getLibro(), vers_aux.getCapitulo(), vers_aux.getVersiculos());
                    if(versiculos.isEmpty()){
                        throw new Exception("Verse "+line+" not found");
                    }
                    if(versiculos.size() != vers_aux.getVersiculos().size()){
                        throw new Exception("Some verses of "+line+" were not found. Returned verses: "+versiculos);
                    }
                    String texto = String.join(versesDelimiter, versiculos.stream().map(Versiculo::getTexto).collect(Collectors.toList()));
                    if(versiculoActual%columnas==0){
                        s.append("<tr><td>").append(texto).append("<br />").append(line).append("</td>");
                    }else if(versiculoActual%columnas==columnas-1){
                        s.append("<td>").append(texto).append("<br />").append(line).append("</td></tr>");
                    }else{
                        s.append("<td>").append(texto).append("<br />").append(line).append("</td>");
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
            Files.write(Paths.get("C:\\Users\\edoo\\IdeaProjects\\biblia\\src\\main\\resources\\output.html"), s.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private VersiculoPlano parse(String entrada) throws Exception {
        // Now create matcher object.
        Matcher enumeration = verseEnumerationPattern.matcher(entrada);
        if (enumeration.find( )) {
            return new VersiculoPlano(
                    enumeration.group(1),
                    Integer.parseInt(enumeration.group(2)),
                    Arrays.stream(enumeration.group(3).split(",")).map(versiculo ->  Integer.valueOf(versiculo)).collect(Collectors.toList())
            );
        } else {
            Matcher interval = versesIntervalPattern.matcher(entrada);
            if(interval.find()){
                return new VersiculoPlano(
                        interval.group(1),
                        Integer.parseInt(interval.group(2)),
                        IntStream.rangeClosed(Integer.parseInt(interval.group(3)), Integer.parseInt(interval.group(4))).boxed().collect(Collectors.toList()));
            }else {
                throw new Exception("Versículo " + entrada + " mal escrito!");
            }
        }
    }
}
