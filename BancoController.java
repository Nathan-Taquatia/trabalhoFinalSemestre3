package com.example.trabalhofinalhgnathan.controller;

/*
@Controller
@RequestMapping("/banco-controller")*/
public class BancoController {
    
    //@Autowired
    //private final BancoService bancoService;

    public BancoController() {
        //this.bancoService = bancoService;
    }

    /*
    @PostMapping("/cadastrar")
    public ResponseEntity<Banco> cadastrar(@RequestBody Banco banco) {
        
        try {
            Banco bancoCadastrado = bancoService.cadastrar(banco);
            return ResponseEntity.status(HttpStatus.CREATED).body(bancoCadastrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        
        return null;
    }
    */

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanco(@PathVariable int id) {
        boolean removed = bancoService.deleteById(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    */

    /*
    @GetMapping("/editar/{id}")
    public String buscaPorId(@PathVariable("id") int id, Model model) {
        Banco busca = bancoService.findById(cep);
        model.addAttribute("id", busca);
        return "editar-banco";
    }
    */

    /*
    @GetMapping("/bancos/search")
    public ResponseEntity<List<Banco>> search(@RequestParam("q") String q) {
    List<Banco> results = bancoService.searchByName(q);
    return ResponseEntity.ok(results);
    }
    */

    /* 
    @GetMapping("/bancos/codigo/{codigo}")
    public ResponseEntity<Banco> getByCodigo(@PathVariable Integer codigo) {
    return bancoService.findByCodigo(codigo)
    .map(ResponseEntity::ok)
    .orElse(ResponseEntity.notFound().build());
    }
    */

    /*
    @GetMapping("/bancos")
    public ResponseEntity<List<Banco>> getAll() {
    return ResponseEntity.ok(bancos); // `bancos` é List<Banco> mantida no controller/serviço
    }

    public Page<Banco> findAll(Pageable pageable) {
    return repo.findAll(pageable);
    }
    */

    /*
    @GetMapping("/editar/{banco}")
    public String buscaPorBanco(@PathVariable("cep") Integer cep, Model model) {
        Cep busca = cepService.findById(cep);
        model.addAttribute("cep", busca);
        return "editar-cep";
    }

    @PutMapping("/bancos/{id}")
    public ResponseEntity<Banco> updateBanco(@PathVariable Integer id, @RequestBody @Valid Banco banco) {
    return bancoService.findById(id)
        .map(existing -> {
            banco.setId(id); // garante o id correto
            Banco saved = bancoService.save(banco);
            return ResponseEntity.ok(saved);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/bancos/{id}")
    public ResponseEntity<Banco> patchBanco(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
    return bancoService.findById(id)
        .map(existing -> {
            if (updates.containsKey("nome")) existing.setNome((String) updates.get("nome"));
            if (updates.containsKey("codigo")) existing.setCodigo((Integer) updates.get("codigo"));
            if (updates.containsKey("cnpj")) existing.setCnpj((String) updates.get("cnpj"));
            Banco saved = bancoService.save(existing);
            return ResponseEntity.ok(saved);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    public Banco editar (int id, String nome, int codigo, String cnpj) {
        Banco bancoBusca = this.busca(id);

        bancoBusca.setId(banco.getCidade());
        bancoBusca.setUf(cep.getUf());
        bancoBusca.setLogradouro(cep.getLogradouro());

        cepRepository.save(cepBusca);

        return cepBusca;
    }

    ARRUMAR OUTRAS FUNÇÕES CONFORME NECESSÁRIO
    */
}
