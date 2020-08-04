import groovy.sql.Sql
import groovy.transform.Field
import groovy.time.TimeCategory 
import groovy.time.TimeDuration

@Field
def sql = Sql.newInstance("jdbc:mysql://localhost:3306/receita_federal", "admin","Password.123", "com.mysql.jdbc.Driver")
//def sql = Sql.newInstance("jdbc:mysql://localhost:3306/receita_federal", "klooks","7J2GJeaiihRW0ElOYDLo", "com.mysql.jdbc.Driver")
long startTime = System.currentTimeSeconds()
println(System.getProperty("java.class.path"))
// Carregar tabela empresa
def carregar_tabela_empresa() {
    //String filepath = "/home/lucas/Documents/Dados Publicos - CNPJ/Amostra/Amostra/amostra.csv"
    //String filepath = "/home/klooks/data/dados_receita_federal/empresa.csv"
    String filepath = "/home/lucas/Documents/Dados Publicos - CNPJ/Amostra/Amostra 100/amostra100.csv"
    file = new File(filepath)
    File profiling = new File("profiling_duplicados.txt")
    String adicionar
    String consulta = 'select id from empresa where cnpj ='
    int contador_de_empresas_duplicadas = 1
    int empresas_adicionadas = 1
    int count = 1
    String reg = ',(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*\$)'

    file.eachLine { String line ->
        profiling.append("empresa "+ count.toString() +"\n\n")
        //divide a linha 
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Date start_a = new Date()
        String[] linha = line.split(reg,-1) 
        Date stop_a = new Date()
        profiling.append("split linha = " + TimeCategory.minus( stop_a, start_a ) + "\n")
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        if (!linha[0].equals("cnpj")) {    // Ignora a primeira linha

            // Consulta duplicados 
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Date start_b = new Date()
            def duplicados = sql.firstRow(consulta+'"'+linha[0]+'"'+';')
            Date stop_b = new Date()
            profiling.append("consulta duplicados  = " + TimeCategory.minus( stop_b, start_b ) + "\n")
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


            if(duplicados == null) { // so insere na tabela se nao for duplicado
            
            def insertSql = 'insert into empresa (version,cnpj,matriz_filial,razao_social,nome_fantasia,' +
                    'situacao_cadastral,data_situacao_cadastral,motivo_situacao_cadastral,nome_cidade_exterior,' +
                    'codigo_natureza_juridica,data_inicio_atividade,cnae_fiscal,descricao_tipo_logradouro,logradouro' +
                    ',numero,complemento,bairro,cep,uf,codigo_municipio,municipio,telefone_1,telefone_2,ddd_fax,email' +
                    ',qualificacao_do_responsavel,capital_social,porte,opcao_pelo_simples,data_opcao_pelo_simples' +
                    ',data_exclusao_do_simples,opcao_pelo_mei,situacao_especial,data_situacao_especial) values ' +
                    '(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)'
            def params = [0,linha[0],linha[1],linha[2],linha[3],linha[4],linha[5],linha[6],linha[7],linha[10],linha[11],linha[12],linha[13],linha[14],linha[15],linha[16],linha[17],linha[18],linha[19],linha[20],linha[21],linha[22],linha[23],linha[24],linha[25],linha[26],linha[27],linha[28],linha[29],linha[30],linha[31],linha[32],linha[33],linha[34]]
            
            // Inserção 
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Date start_c = new Date()
            def keys = sql.executeInsert insertSql, params
            Date stop_c = new Date()
            profiling.append("Inserção  = " + TimeCategory.minus( stop_c, start_c ) + "\n")
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            empresas_adicionadas++
            }
            else {
                contador_de_empresas_duplicadas++
                println("duplicadas: "+contador_de_empresas_duplicadas)
            }


        }
        profiling.append("--------------------------------------\n")
        ++count
    }
    println("contador de empresas duplicadas = "+contador_de_empresas_duplicadas)
    println(empresas_adicionadas)
}


def carregar_tabela_socios(){
    String filepath = "/home/lucas/Documents/Dados Publicos - CNPJ/Amostra/Amostra/amostra_socio.csv"
    //String filepath = "/home/klooks/data/dados_receita_federal/socio.csv"
    //String filepath = "/home/lucas/Documents/Dados Publicos - CNPJ/Amostra/Amostra 100/amostra_socio100.csv"
    file = new File(filepath)
    String consulta = 'select id from empresa where cnpj = "'
    String consulta_socio = 'select id from socio where nome_socio = "'
    String id_empresa
    String adicionar
    String reg = ',(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*\$)'
    File dump = new File("empresas_missing_socios.csv")
    dump.append("cnpj,identificador_de_socio,nome_socio,cnpj_cpf_do_socio,codigo_qualificacao_socio," +
            "percentual_capital_social,data_entrada_sociedade,cpf_representante_legal,nome_representante_legal," +
            "codigo_qualificacao_representante_legal\n")

    file.eachLine { String line ->
        String[] linha = line.split(reg,-1) // divide a linha
        if (!linha[0].equals("cnpj")) {    // Ignora a primeira linha
            // procura o id_empresa referente ao cnpj
            def row = sql.firstRow(consulta + linha[0] + '"')

            // Caso a empresa não seja encontrada
            if (row.equals(null)) {
                linha.each {it ->
                    dump.append(it)
                    if(!it.equals(linha[9]))
                        dump.append(",")
                }
                dump.append("\n")

            }

            // Caso a empresa seja encontrada
            else {
                id_empresa = "${row.id}"
                /*adicionar = "insert into socios (identificador_de_socio,nome_socio,cnpj_cpf_do_socio,codigo_qualificacao_socio,percentual_capital_social,data_entrada_sociedade,cpf_representante_legal,nome_representante_legal,codigo_qualificacao_representante_legal,id_empresa) " +
                        "values ('"+linha[1]+"','"+linha[2]+"','"+linha[3]+"','"+linha[4]+"','"+linha[5]+"','"+linha[6]+"','"+linha[7]+"','"+linha[8]+"','"+linha[9]+"','"+id_empresa+"');"
                println(adicionar)*/
                def insertSql = 'insert into socio (version,identificador_de_socio,nome_socio,cnpj_cpf_do_socio,' +
                        'codigo_qualificacao_socio,percentual_capital_social,data_entrada_sociedade,' +
                        'cpf_representante_legal,nome_representante_legal,' +
                        'codigo_qualificacao_representante_legal,empresa_id) values (?,?,?,?,?,?,?,?,?,?,?)'
                def params = [0,linha[1],linha[2],linha[3],linha[4],linha[5],linha[6],linha[7],linha[8],linha[9],id_empresa]
                if (linha[2][-1] == '"' &&  linha[2][0] == '"') linha[2] = linha[2][1..-2] // bugfix caso nome aspas duplicadas 
                def duplicados = sql.firstRow(consulta_socio+linha[2]+'" AND cnpj_cpf_do_socio ="'+linha[3]+'";')
                if(duplicados != null) linha
                if(duplicados == null) def keys = sql.executeInsert insertSql, params
            }

        }
    }
    
}

def carregar_tabela_cnae(){
    String filepath = "/home/lucas/Documents/Dados Publicos - CNPJ/Amostra/Amostra/CNAEs.csv"
    //String filepath = "/home/klooks/data/dados_receita_federal/CNAEs.csv"
    file = new File(filepath)

    file.eachLine { String line ->
        String[] linha = line.split('\t',-1)

        linha[1] = linha[1].replace('.',''); linha[1] = linha[1].replace('-','')
        def insertSql = 'insert into cnae (version,code,description) values (?,?,?)'
        def params = [0,linha[1],linha[2]]
        def keys = sql.executeInsert insertSql, params


    }

}
def carregar_empresa_cnae() {
    //String filepath = "/home/lucasmarra_server/dados/Amostra_mini/amostra_cnae100.csv"
    String filepath = "/home/lucas/Documents/Dados Publicos - CNPJ/Amostra/Amostra/amostra_cnae.csv"
    file = new File(filepath)
    String consulta_empresa = 'select id from empresa where cnpj = "'
    String consulta_cnae = 'select id from cnae where code = "'
    String id_empresa
    String id_cnae
    String adicionar
    File dump = new File("empresas_missing_cnae.csv")
    dump.append("cnpj,cnae\n")

    file.eachLine { String line ->
        String[] linha = line.split(',',-1)
        String consulta_cnae_empresa = 'select * from empresa_cnae where empresa_cnae_id = '
        if (!linha[0].equals("cnpj")) {

            // procura o id_empresa referente ao cnpj
            def row = sql.firstRow(consulta_empresa + linha[0] + '"')
            def row2 = sql.firstRow(consulta_cnae + linha[1] + '"')


            // Caso a empresa não seja encontrada
            if (row.equals(null)) {
                linha.each {it ->
                    dump.append(it)
                    if(!it.equals(linha[2]))
                        dump.append(",")
                }
                dump.append("\n")

            }

            // Caso a empresa seja encontrada
            else {
                id_empresa = "${row.id}"
                id_cnae = "${row2.id}"
                //adicionar = "insert into cnae (cnae,primaria,id_empresa) values ('" + linha[1] + "','" + linha[2] + "','" + id_empresa + "');"
                //sql.execute(adicionar)
                def duplicados = sql.firstRow(consulta_cnae_empresa+id_empresa+" AND cnae_id = "+id_cnae+';')
                def insertSql = 'insert into empresa_cnae (empresa_cnae_id,cnae_id) values (?,?)'
                def params = [id_empresa,id_cnae]
                if(duplicados == null) def keys = sql.executeInsert insertSql, params
                

            }



        }
    }
}


//carregar_tabela_cnae()
carregar_tabela_empresa()
//carregar_empresa_cnae()
//carregar_tabela_socios()


long endTime   = System.currentTimeSeconds()
long totalTime = endTime - startTime;
System.out.println(totalTime);
