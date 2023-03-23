package com.devhgomes.contentcalendar.controller;

import com.devhgomes.contentcalendar.model.Content;
import com.devhgomes.contentcalendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/content")
public class ContentController {
    private final ContentCollectionRepository repository;
    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }
    @GetMapping("")
    public List<Content> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createContent(@Valid @RequestBody Content content){
        repository.save(content);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateContent(@RequestBody Content content, @PathVariable Integer id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping ("/{id}")
    public void deleteContent(@PathVariable Integer id){
        repository.deleteById(id);
    }
}
