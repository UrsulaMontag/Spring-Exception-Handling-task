package de.neuefische.springexceptionhandlingtask;

import de.neuefische.springexceptionhandlingtask.exceptions.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @GetMapping("{brand}")
    String getCarBrand(@PathVariable String brand) throws IllegalArgumentException {
        if (!brand.equals("porsche")) {
            throw new IllegalArgumentException("Only 'porsche' allowed");
        }
        return brand;
    }

    @GetMapping
    String getAllCars() {
        throw new NoSuchElementException("No cars found");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
