package com.example.mill;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mill.bean.MillState;

@RestController()
@EnableScheduling
@RequestMapping("/rest/mill")
public class MillController {

    private final MillService millService;

    public MillController(MillService millService) {
        this.millService = millService;
    }

    @GetMapping()
    public MillState state() {
        return millService.getState();
    }

    @PostMapping(path = "water/{capacity}")
    public void water(@PathVariable Integer capacity) {
        millService.addWater(capacity);
    }

    @PostMapping(path = "millet/{capacity}")
    public void millet(@PathVariable Integer capacity) {
        millService.addMillet(capacity);
    }

    @PostMapping(path = "flour/drop")
    public void millet() {
        millService.dropFlour();
    }
}
