import java.nio.file.Files
import java.nio.file.Paths

String pwd = "pwd".execute().text.replaceAll("\\s","")+"/";
def ls = "ls".execute()
String[] ls_linha = ls.text.toString().replaceAll("organizer.groovy","organizer").split('\n')
def map = []

for (int i = 0; i < ls_linha.size(); i++){
        String[] nome_ext = ls_linha[i].split('\\.')

        if((!map.contains(nome_ext.last())) && nome_ext.size() > 1){
            File f = new File(nome_ext.last())
            f.mkdir()
            map.add(nome_ext.last())
        }
        if(nome_ext.size() > 1) {
            try {
                Files.move(Paths.get(pwd+ls_linha[i]),Paths.get(pwd+nome_ext.last()+"/"+ls_linha[i]))
            }
            catch (Exception e){

            }
        }
}
//
