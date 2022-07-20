import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
  
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        // fazer uma conexão HTTP e buscar os Top 250 Filmes
        // String url = "https://api.mocki.io/v2/549a5d8b";
        // ExtratorDeConteudoDoIMDB extrator = new ExtratorDeConteudoDoIMDB();
        
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        

        for(int i = 0; i<3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            var geradora = new GeradorDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
