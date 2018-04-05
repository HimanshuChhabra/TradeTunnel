package com.ip.tradetunnel.entities.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ip.tradetunnel.entities.Image;
import com.ip.tradetunnel.entities.Product;
import com.ip.tradetunnel.entities.enumerations.Status;
import com.ip.tradetunnel.entities.repos.ImageRepository;
import com.ip.tradetunnel.entities.repos.ProductRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RepositoryRestController
@RequestMapping("/persistImage")
public class PersistImageController {

	private static String UPLOADED_FOLDER = "/Users/himanshuchhabra/Documents/testImages/";

	@Autowired
	ProductRepository prodRepo;

	@Autowired
	ImageRepository imageRepo;

	@PostMapping("/single")
	private ResponseEntity<PersistentEntityResource> updateProductStatus(@RequestParam("file") MultipartFile uploadfile,
			@RequestParam("prodId") Long id, PersistentEntityResourceAssembler assembler) {
		Product product = prodRepo.findOne(id);

		try {
			saveUploadedFiles(Arrays.asList(uploadfile), product);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		//return new ResponseEntity("Successfully uploaded - " + uploadfile.getOriginalFilename(), new HttpHeaders(),
		//		HttpStatus.OK);
		return ResponseEntity.ok(assembler.toResource(product));

	}

	private void saveUploadedFiles(List<MultipartFile> files, Product prod) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; // next pls
			}

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			Image image = new Image();
			image.setData(bytes);
			image.setImageName(UPLOADED_FOLDER + file.getOriginalFilename());
			image.setProduct(prod);
			imageRepo.save(image);
			
			prod.getImage().add(image);
			prodRepo.save(prod);

		}
	}
}
