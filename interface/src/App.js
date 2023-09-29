import './App.css';

import {useState, useEffect} from 'react';
import {BsTrash,BsBookmarkCheck, BsBookmarkCheckFill} from 'react-icons/bs';

const API = "http://localhost:8080/api";

function App() {
  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [dataPrevista, setDataPrevista] = useState();
  const [dataConclusao, setDataConclusao] = useState();
  const [todos, setTodos] = useState([]);
  const [loading,setLoading] = useState(false);

  const timeElapsed = Date.now();
  const today = new Date(timeElapsed);

  //Load to-do on page load
  useEffect(()=>{

    const loadData = async() => {

      setLoading(true);
      
      const res = await fetch(API + "/tarefas")
      .then((res) => res.json())
      .then((data) =>data)
      .catch((err)=> console.log(err));
      
  
      setLoading(false);

      setTodos(res);
    }

    loadData();
  },[]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const todo = {
      id: "",
      titulo,
      descricao,
      status: {
        "id": 1,
        "titulo": "Pendente",
        "descricao": "Atividade ainda a ser feita."
    },
      categoria: {
        "id": 2,
        "titulo": "Atividade",
        "descricao": "Atividades a ser realizada"
    },
      dataPrevista,
      dataConclusao
      
    };

    await fetch(API + "/tarefas",{
      method: "POST",
      body: JSON.stringify(todo),
      headers: {
        "Content-Type": "application/json",
      }
    });

    setTodos((prevState) =>[...prevState, todo]);

    setTitulo("");
    setDescricao("");
    setDataPrevista(null);
  };

  const handleDelete = async (id) =>{
    await fetch(API + "/tarefas/"+id,{
      method: "DELETE",
    });

    setTodos((prevState) => prevState.filter((todo) => todo.id !== id));
  }

  const handleEdit = async (todo) =>{
  
    todo.status.id === 1 ? todo.status.id = 2: todo.status.id = 1;
    todo.status.titulo === "Pendente" ?  todo.status.titulo = "Concluido" : todo.status.titulo = "Pendente" ;
    todo.status.descricao === 1 ? todo.status.descricao = "Atividade ainda a ser feita." : todo.status.descricao = "Atividade realizada com Sucesso!";

    todo.dataConclusao === null ? todo.dataConclusao =today.toLocaleDateString():todo.dataConclusao = null;
    
    await fetch(API + "/tarefas/"+todo.id,{
      method: "PUT",
      body: JSON.stringify(todo),
      headers: {
        "Content-Type": "application/json",
      }
    });
    

    console.log(todo);
    setTodos((prevState) => prevState.map((todo) => todo));
    //setTodos((prevState) => prevState.map((t) => (t.id === data.id ? (t = data): t)));
  }

  if(loading){
    return <p>Carregando...</p>
  }

  return (
    <div className="App">
      <div className="todo-header">
        <h1>DRA - Projeto Lista de Tarefas</h1>
      </div>
      <div className="form-todo">
        <h2>Insira a sua próxima tarefa:</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-control">
            <label htmlFor="titulo">O que você vai fazer?</label>
            <input 
              type="text" 
              name="titulo" 
              placeholder="Título da terefa" 
              onChange={(e)=> setTitulo(e.target.value)}
              value={titulo || ""}
              required
            />
          </div>
          <div className="form-control">
            <label htmlFor="descricao">Descrição:</label>
            <input 
              type="text" 
              name="descricao" 
              placeholder="Descrição da atividade" 
              onChange={(e)=> setDescricao(e.target.value)}
              value={descricao || ""}
              required
            />
          </div>
          <div className="form-control">
            <label htmlFor="dataPrevista">Data Prevista:</label>
            <input 
              type="date" 
              name="dataPrevista" 
              onChange={(e)=> setDataPrevista(e.target.value)} 
              value={ dataPrevista || ""}
              required
            />
          </div>
          <input type="submit" value="Criar Tarefa"/>
        </form>
      </div>
      
      <div className="list-todo">
        <h2>Lista de tarefas:</h2>
        {todos.length === 0 && <p>Não há taferas!</p>}
        {todos.map((todo) =>(
          <div className="todo" key={todo.titulo}>
            <h3 className={todo.status ? "todo-h3" : "todo-status"}>{todo.titulo}</h3>
            <p><strong>Descrição:</strong> {todo.descricao}</p>
            <p><strong>Status:</strong> {todo.status.titulo}</p>	
            <p>Data Prevista: {todo.dataPrevista}</p>
            <p>Data dataConclusao: {todo.dataConclusao}</p>
            <div className="actions">
              <span onClick={()=> handleEdit(todo)}>
                {todo.status.id === 1 ? <BsBookmarkCheck/> : <BsBookmarkCheckFill/>}
   
              </span>
              <BsTrash onClick={()=> handleDelete(todo.id)}/>  
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
