const amqp = require('amqplib');

const fila = 'PRECO';

amqp.connect( { 
    hostname: 'localhost',
    port: 5672,
    username: 'admin',
    password: 12345
})
.then((conexao)=> {
    conexao.createChannel()
        .then((canal)=>{
            canal.consume(fila, (mensagagem)=> {
                console.log(mensagagem.content.toString())
            })
    })

}).catch((erro) => console.log(erro))