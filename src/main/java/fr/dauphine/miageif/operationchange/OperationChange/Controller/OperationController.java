package fr.dauphine.miageif.operationchange.OperationChange.Controller;

import fr.dauphine.miageif.operationchange.OperationChange.Model.OperationChange;
import fr.dauphine.miageif.operationchange.OperationChange.Repository.OperationChangeRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OperationController {

    // Spring se charge de la création d'instance
    @Autowired
    private OperationChangeRepository repository;

    // POST
    // curl -X POST -H "Content-type: application/json" -d "{\"source\" : \"EUR\", \"dest\" : \"USD\", \"montant\" : 500, \"date\": \"2021-06-23\"}" "http://localhost:8080/operation-change"
    @PostMapping(value = "/operation-change",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<OperationChange> createOperationChange (@RequestBody OperationChange cpe_change) throws JSONException {

        if (cpe_change.getTaux() == null){
            String baseUrl = "http://localhost:8000/taux-change/source/" + cpe_change.getSource() + "/dest/" + cpe_change.getDest() + "/date/" + cpe_change.getDate();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response=null;

            try{
                response=restTemplate.exchange(baseUrl,
                        HttpMethod.GET, getHeaders(),String.class);
            }catch (Exception ex)
            {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            JSONObject jsonObj = new JSONObject(response.getBody());
            BigDecimal taux = new BigDecimal(jsonObj.getString("taux"));

            OperationChange oc = new OperationChange(cpe_change.getSource(), cpe_change.getDest(), cpe_change.getMontant(), taux, cpe_change.getDate(),cpe_change.getCounterpart());

            try {
                OperationChange _operationChange = repository.save(oc);
                return new ResponseEntity<>(_operationChange, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        OperationChange _operationChange = repository.save(cpe_change);
        return new ResponseEntity<>(_operationChange, HttpStatus.CREATED);

    }

    // curl -X GET "http://localhost:8080/operation-change"
    @GetMapping("/operation-change")
    public ResponseEntity<List<OperationChange>> getAllOperationChange(){
        try {
            List<OperationChange> operationChanges = new ArrayList<OperationChange>();
            repository.findAll().forEach(operationChanges::add);

            if (operationChanges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(operationChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // curl -X GET "http://localhost:8080/operation-change/id/1234"
    @GetMapping("/operation-change/id/{id_transaction}")
    public ResponseEntity<OperationChange> getOperationChangeById(@PathVariable Long id_transaction) throws RestClientException, IOException {
        Optional<OperationChange> operationChange = repository.findById(id_transaction);

        if (operationChange.isPresent()) {
            return new ResponseEntity<>(operationChange.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // curl -X GET "http://localhost:8080/operation-change/montant/1000"
    @GetMapping("/operation-change/montant/{montant}")
    public ResponseEntity<List<OperationChange>> getOperationChangeByMontant(@PathVariable Integer montant) throws RestClientException, IOException {
        try{
            List<OperationChange> operationChanges = repository.findByMontant(montant);

            if (operationChanges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(operationChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // curl -X GET "http://localhost:8080/operation-change/counterpart/HSBC"
    @GetMapping("/operation-change/counterpart/{counterpart}")
    public ResponseEntity<List<OperationChange>> getOperationChangeByCounterpart(@PathVariable String counterpart) throws RestClientException, IOException {
        try{
            List<OperationChange> operationChanges = repository.findByCounterpart(counterpart);

            if (operationChanges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(operationChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // curl -X GET "http://localhost:8080/operation-change/date/2021-06-21"
    @GetMapping("/operation-change/date/{date}")
    public ResponseEntity<List<OperationChange>> getOperationChangeByDate(@PathVariable String date) throws RestClientException, IOException {
        try{
            List<OperationChange> operationChanges = repository.findByDate(date);

            if (operationChanges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(operationChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // curl -X GET "http://localhost:8080/operation-change/taux/132.30"
    @GetMapping("/operation-change/taux/{taux}")
    public ResponseEntity<List<OperationChange>> getOperationChangeByTaux(@PathVariable BigDecimal taux) throws RestClientException, IOException {
        try{
            List<OperationChange> operationChanges = repository.findByTaux(taux);

            if (operationChanges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(operationChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // curl -X GET "http://localhost:8080/operation-change/source/EUR/dest/USD"
    @GetMapping("/operation-change/source/{source}/dest/{dest}")
    public ResponseEntity<List<OperationChange>> getOperationChangeBySourceAndDest(@PathVariable String source, @PathVariable String dest) throws RestClientException, IOException {
        try{
            List<OperationChange> operationChanges = repository.findBySourceAndDest(source, dest);

            if (operationChanges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(operationChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // curl -X GET "http://localhost:8080/operation-change/source/EUR/dest/USD/date/2021-06-23"
    @GetMapping("/operation-change/source/{source}/dest/{dest}/date/{date}")
    public ResponseEntity<List<OperationChange>> getOperationChangeBySourceAndDestAndDate(@PathVariable String source, @PathVariable String dest, @PathVariable String date) throws RestClientException, IOException {
        try{
            List<OperationChange> operationChanges = repository.findBySourceAndDestAndDate(source, dest,date);

            if (operationChanges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(operationChanges, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
