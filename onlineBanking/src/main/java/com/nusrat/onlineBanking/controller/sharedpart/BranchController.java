package com.nusrat.onlineBanking.controller.sharedpart;

import com.nusrat.onlineBanking.entities.sharedEntities.Branch;
import com.nusrat.onlineBanking.service.sharedService.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branchs")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/")
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable Long id) {
        return branchService.getBranchById(id);
    }

    @PostMapping("/create")
    public Branch createBranch(@RequestBody Branch branch) {
        return branchService.createBranch(branch);
    }

    @PutMapping("/{id}")
    public Branch updateBranch(
            @PathVariable Long id,
            @RequestBody Branch branchDetails) {
        return branchService.updateBranch(id, branchDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.ok("Branch deleted successfully.");
    }
}
