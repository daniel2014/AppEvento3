package com.drs.AppEvento3.controller;

import com.drs.AppEvento3.models.Convidado;
import com.drs.AppEvento3.models.Evento;
import com.drs.AppEvento3.repository.ConvidadoRepository;
import com.drs.AppEvento3.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EventoController {

    @Autowired //Injeção de Depedência
    private EventoRepository er; //er = EventoRepository

    @Autowired //Injeção de Depedência
    private ConvidadoRepository cr; //cr = ConvidadoRepository

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET )
    public String form(){
        return "evento/formEventos";

    }@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST )
    public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os Campos");
            return "redirect:/cadastrarEvento";
        }
        er.save(evento);
        attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
        return "redirect:/cadastrarEvento";
    }

    //Renderizar dados na página index
    @RequestMapping("/eventos")
    public ModelAndView listaEventos(){
        Iterable<Evento> eventos = er.findAll(); //Busca uma lista de todos os Eventos
        ModelAndView mv = new ModelAndView("index"); //Renderiza na página index
        mv.addObject("evento", eventos); // "" representa o valor definido na página index <div th:each="evento : ${evento}"> e eventos representa Iterable<Evento> eventos = er.findAll();
        return mv; //mv = ModelAndView

    }

    //Redireciona para página de cada Evento | Quando o usuário clicar em cima do nome do evento ele vai redirecionar para o respectivo código dos detalhes do evento
    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("eventos", evento);

        Iterable<Convidado> convidados = cr.findByEvento(evento);//Listando usuários do Evento
        mv.addObject("convidados", convidados);
        return mv;
    }

    @RequestMapping("/deletarEvento2")
    public String deletarEvento(long codigo){
        Evento evento = er.findByCodigo(codigo);
        er.delete(evento);
        return "redirect:/eventos";
    }

    //Relacionando Convidados na Lista de Evento
    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/{codigo}";
        }
        Evento evento = er.findByCodigo(codigo); //Buscando código de referência do evento no banco de dados
        convidado.setEvento(evento);
        cr.save(convidado);
        attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
        return "redirect:/{codigo}";
    }

    @RequestMapping("/deletarConvidado")
    public String deletarConvidado(String rg){ //Através do "rg" vamos encontrar o convidado correspondente no Evento
        Convidado convidado = cr.findByRg(rg);
        cr.delete(convidado); //Deletar Convidado

        //Retornar a lista de convidados atualizada
        Evento evento = convidado.getEvento(); // Pegando o Código do Evento
        long codigoLong = evento.getCodigo(); // Acessandom o Código do Evento
        String codigo = "" + codigoLong; // Devolvendo para tipo String
        return "redirect:/" + codigo; // Retornar para mesma página com dados atualizados
    }


}
