package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ApiController {
    private final List<String> messages = new ArrayList<>();
    // curl -XGET http://localhost:8080/messages
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }
    //curl -XPOST http://localhost:8080/messages -d "post"
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }
    //curl -XGET http://localhost:8080/messages/0
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }
    //curl -XDELETE http://localhost:8080/messages/0
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    //я не знаю почему, у меня не работает ничего из приложенных материалов :(
    @PutMapping("messages")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
    //-X GET http://localhost:8080/messages/search/text
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> searchText (@PathVariable String text) {
        int index = 0;
        for (int i = 0; i < messages.size(); i ++){
            if (messages.get(i).contains(text)){
                index = i;
                break;
            }
        }

       return ResponseEntity.ok(index);
    }
    //-X GET http://localhost:8080/messages/count
    @GetMapping("messages/count")
    public ResponseEntity<Integer> searchText (){
        int count = messages.size();
        return ResponseEntity.ok(count);
    }
    // curl -X POST http://localhost:8080/messages/0/create как я понял имелось в виду что индексы должны быть последовательным, ну в противно случае ясно как доделывается
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> addIMessage(@PathVariable Integer index) {
        messages.add(index, "");
        return ResponseEntity.accepted().build();
    }
    //curl -X DELETE http://localhost:8080/messages/search/a
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteByText (@PathVariable String text) {
        List<Integer> indexs = new ArrayList<>();
        for (int i = 0; i < messages.size(); i ++){
            if (messages.get(i).contains(text)){
                indexs.add(i);

            }
        }
        for (Integer i: indexs){
            messages.remove((int) i);
        }

        return ResponseEntity.accepted().build();
    }


}
