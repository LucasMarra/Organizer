insert into empresas (cnpj,identificador_matriz_filial,razao_social,nome_fantasia, situacao_cadastral,data_situacao_cadastral,motivo_situacao_cadastral,nome_cidade_exterior, codigo_natureza_juridica,data_inicio_atividade,cnae_fiscal,descricao_tipo_logradouro,logradouro ,numero,complemento,bairro,cep,uf,codigo_municipio,municipio,ddd_telefone_1,ddd_telefone_2,ddd_fax ,qualificacao_do_responsavel,capital_social,porte,opcao_pelo_simples,data_opcao_pelo_simples ,data_exclusao_do_simples,opcao_pelo_mei,situacao_especial,data_situacao_especial,email) values  ('123','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','');

banco do brasil 
insert into empresa (version,cnpj,identificador_matriz_filial,razao_social,nome_fantasia, situacao_cadastral,data_situacao_cadastral,motivo_situacao_cadastral,nome_cidade_exterior, codigo_natureza_juridica,data_inicio_atividade,cnae_fiscal,descricao_tipo_logradouro,logradouro ,numero,complemento,bairro,cep,uf,codigo_municipio,municipio,ddd_telefone_1,ddd_telefone_2,ddd_fax ,qualificacao_do_responsavel,capital_social,porte,opcao_pelo_simples,data_opcao_pelo_simples ,data_exclusao_do_simples,opcao_pelo_mei,situacao_especial,data_situacao_especial,email) values  (0,"00000000000191","1","BANCO DO BRASIL SA","DIRECAO GERAL","2","2005-11-03","0","","2038","1966-08-01","6422100","QUADRA","SAUN QUADRA 5 LOTE B TORRES I II E III","SN","ANDAR 1 A 16              SALA  101 A 1601          ANDAR 1 A 16              SALA  101 A 1601          ANDAR 1 A 16              SALA  101 A 1601","ASA NORTE","70040912","DF","9701","BRASILIA","61  34939002","","61  34931040","10","67000000000.00","05","0","","","0","","","SECEX@BB.COM.BR");

insert into cnae (cnae,primaria,empresas_id) values ('6499999','f',1);
00000000000191,6499999,f


Grails 3: 
insert into empresa (cnae_fiscal,version,cnpj,matriz_filial,razao_social,nome_fantasia, situacao_cadastral,data_situacao_cadastral,motivo_situacao_cadastral,nome_cidade_exterior, codigo_natureza_juridica,data_inicio_atividade,descricao_tipo_logradouro,logradouro ,numero,complemento,bairro,cep,uf,codigo_municipio,municipio,telefone_1,telefone_2,ddd_fax ,qualificacao_do_responsavel,capital_social,porte,opcao_pelo_simples,data_opcao_pelo_simples ,data_exclusao_do_simples,opcao_pelo_mei,situacao_especial,data_situacao_especial,email) values  ("6422100",0,"00000000000191","1","BANCO DO BRASIL SA","DIRECAO GERAL","2","2005-11-03","0","","2038","1966-08-01","QUADRA","SAUN QUADRA 5 LOTE B TORRES I II E III","SN","ANDAR 1 A 16              SALA  101 A 1601          ANDAR 1 A 16              SALA  101 A 1601          ANDAR 1 A 16              SALA  101 A 1601","ASA NORTE","70040912","DF","9701","BRASILIA","61  34939002","","61  34931040","10","67000000000.00","05","0","","","0","","","SECEX@BB.COM.BR");


insert into cnae (version,code,description) values (0,"6422100","Bancos múltiplos, com carteira comercial");
insert into cnae (version,code,description) values (0,"6499999","Outras atividades de serviços financeiros não especificadas anteriormente");

insert into empresa_cnae (empresa_cnae_id,cnae_id) values (1,1);
insert into empresa_cnae (empresa_cnae_id,cnae_id) values (1,2);


insert into socio (version,identificador_de_socio,nome_socio,cnpj_cpf_do_socio,codigo_qualificacao_socio,percentual_capital_social,data_entrada_sociedade,cpf_representante_legal,nome_representante_legal,codigo_qualificacao_representante_legal,empresa_id) values (0,"2","ANTONIO MAURICIO MAURANO","***022878**","10","0","2012-03-27","","","",1);
insert into socio (version,identificador_de_socio,nome_socio,cnpj_cpf_do_socio,codigo_qualificacao_socio,percentual_capital_social,data_entrada_sociedade,cpf_representante_legal,nome_representante_legal,codigo_qualificacao_representante_legal,empresa_id) values (0,"2","maria da silva","***022878**","8","0","2012-03-27","","","",1);
insert into socio (version,identificador_de_socio,nome_socio,cnpj_cpf_do_socio,codigo_qualificacao_socio,percentual_capital_social,data_entrada_sociedade,cpf_representante_legal,nome_representante_legal,codigo_qualificacao_representante_legal,empresa_id) values (0,"2","joao da silva","***022878**","31","0","2012-03-27","","","",1);


00000000000191,2,ANTONIO MAURICIO MAURANO,***022878**,10,0,2012-03-27,,,,,
00000000000191,2,MARCELO AUGUSTO DUTRA LABUTO,***238081**,10,0,2012-03-27,,,,,

00000000000191,2,RONALDO SIMON FERREIRA,***685018**,10,0,2019-07-29,,,,,
00000000000191,2,ENIO MATHIAS FERREIRA,***078106**,10,0,2018-11-07,,,,,