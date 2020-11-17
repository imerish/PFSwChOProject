package com.imerish.project.controller;

import com.imerish.project.business.domain.Worker;
import com.imerish.project.business.repository.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    private final Logger logger = LoggerFactory.getLogger(WorkerController.class);

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerController(WorkerRepository workerRepository) {

        this.workerRepository = workerRepository;
    }

    @GetMapping
    public String getWorkerListView(Model model) {

        List<Worker> workers = workerRepository.findAll();

        model.addAttribute("workers", workers);
        return "workers";
    }

    @GetMapping(value = "/add")
    public String getAddWorkerForm(Model model) {
        model.addAttribute("worker", new Worker());
        return "add_worker";
    }

    @PostMapping
    public RedirectView getWorkerAdd(@ModelAttribute @Valid Worker worker, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new RedirectView("/worker/add");
        }
        this.workerRepository.save(worker);
        logger.info("Worker added with ID: " + worker.getId());
        return new RedirectView("/worker");
    }

    @PostMapping(value = "/{id}")
    public RedirectView getWorkerDelete(@PathVariable String id) {
        this.workerRepository.deleteById(Long.parseLong(id));
        logger.info("Worker deleted with ID: " + id);
        return new RedirectView("/worker");
    }

    @GetMapping(value = "/{id}/update")
    public String getWorkerUpdateForm(@PathVariable String id, Model model) {
        Worker worker = this.workerRepository.findById(Long.parseLong(id)).orElseGet(Worker::new);
        model.addAttribute("worker", worker);
        return "add_worker";
    }

    @PostMapping(value = "/{id}/update")
    public RedirectView getWorkerUpdate(@ModelAttribute @Valid Worker worker, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new RedirectView("/worker/" + worker.getId() + "/update");
        }
        this.workerRepository.save(worker);
        logger.info("Worker changed with ID: " + worker.getId());
        return new RedirectView("/worker");
    }
}
