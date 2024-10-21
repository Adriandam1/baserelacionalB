
baserelacionalB

A partires da taboa produtos creada no exercio baserelacionalA e usando un resultset de tipo "scrollable, updatable" que devolte todas as filas da taboa , desenvolver catro metodos
que permitan:
- listar o contido completo do resultset
- actualizar dende dentro do resultset : por exemplo a fila do producto p2 facendo que o seu precio pase a ser 8
- inserir dende dentro do resultset unha fila de valores : por exemplo o produto  p4, martelo, 20
- borrar : por exemplo a  fila de codigo p3 tamen dende dentro do resultset

Lembrar que para consultar  con posibilidade de actualizar o resultado do consultado debo usar un obxecto Statement creado cas opcions  "scrollable, updatable", e aplicarlle despois o  metodo executeQuery("consulta") para obter o resultset . E dicir :
Statement statement= conn.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
e despois crear o obxecto ResultSet a partir de aplicar o metodo executeQuery("consulta") a o obxecto Statement anterior

Pasos para inserir un rexistro dende dentro do resultset
invocar o metodo moveToInsertRow() do obxecto ResultSet
introducir valores nos campos do rexistro que queremos inserir
usar metodo updateString("nome_campo", valor) do ResultSet ( se e in enteiro sera updateInt("nome_campo", valor) )
inserir a fila : invocar o metodo insertRow() do obxecto ResultSet

Pasos para actualizar un rexistro os pasos a seguir son (Unha vez situado na fila que queremos modificar do ResultSet):
Actualizar un campo: invocar o metodo updateString("nome_campo", valor) do  ResultSet (se e un enteiro sera :
updateInt("nome_campo", valor))
a continuación actualizar a fila que conten os campos actualizados :
invocar o metodo updateRow()

Pasos para borrar un rexistro (sobre o que estamos situados : invocar o metodo deleteRow()


Alguns  metodos do resultset (scrollable and updatable):
first() : move o cursor a primeira fila do obxecto ResultSet
last() : move o cursor a ultima  fila do obxecto ResultSet
isLast(): retorna un valor verdadeiro se estamos posicionados na ultima fila do obxecto ResultSet
close()
next():   move o cursor a proxima  fila do obxecto ResultSet
previous(): move o cursor a  fila previa a actual do obxecto ResultSet

IMPORTANTE: a consulta de todos os campos dunha fila debe facerse explicitando o nome da tabao antes do * , e decir:  select produtos.* from produtos . . . 


