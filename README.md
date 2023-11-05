# LP2-22202310
Diagrama UML do projeto:
![](UmlDeisiChess.png?raw=true "Diagrama UML")  
  
Personalização do jogo com o tema sitcoms animadas em que as peças pretas são personagens de "The Simpsons" e as peças brancas personagens de "Family Guy", com planos de adicionar mais membros de ambas séries para os outros tipos de peças, para a primeira parte do projeto escolhi as seguintes peças para os reis:  
  
Rei equipa preta:  
![](homerSimpson.png?raw=true "Homer Simpson") (Homer Simpson)  
  
Rei equipa branca:  
![](peterGriffin.png?raw=true "Peter Griffin") (Peter Griffin)  

Exemplo de tabuleiro:  
![](deisiChess.png?raw=true "Tabuleiro") 


  
Em relação às minhas escolhas na modelação optei por criar várias funções auxiliares dentro da GameManager pois simplificava o código dentro das funções obrigatórias.  
Escolhi utilizar dois HashMaps dentro da classe Board com o objectivo de conseguir rapidamente obter a informação sobre cada peça ou coordenadas.  
Nas funções getIdToPiece e getCoordsToId utilizei parametros pois o objectivo de guardar a informação em HashMaps seria sempre obter o value através da key, tendo isso em conta os parametros de ambas as funções são os valores da key e a função dá return do valor associado dentro do HashMap.
