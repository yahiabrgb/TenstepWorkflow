package TenstepWorkflow.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import TenstepWorkflow.entities.Documents;
import TenstepWorkflow.repositories.DocumentsRepository;

@Service
public class DocumentsService {

  @Autowired
  private DocumentsRepository DocumentsRepository;
  @Autowired
  private SendEmailService sendEmailService;

  File file = new File("C:\\Users\\Bourguiba\\Desktop\\11.jpg");

  public Documents store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    Documents FileDB = new Documents(fileName, file.getContentType(), file.getBytes());

    return DocumentsRepository.save(FileDB);
  }

  public Documents getFile(String id) {
    return DocumentsRepository.findById(id).get();
  }
  
  public Stream<Documents> getAllFiles() {
    return DocumentsRepository.findAll().stream();
  }
  public void VerifSignature() throws IOException, MessagingException {
	  try {
          Runtime runTime = Runtime.getRuntime();
          
          String executablePath = "C:\\Users\\Bourguiba\\SigDetect.exe";
          
          Process process = runTime.exec(executablePath);
         
          try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
          InputStream is = process.getInputStream();
          BufferedReader in = new BufferedReader(new InputStreamReader(is));
          String temp;
          String piss;
          Boolean t ;

          while ((temp = in.readLine()) != null ) {
        	  piss = temp.toLowerCase();
        	  t = Boolean.parseBoolean(piss);
        	  System.out.println(t);
        	  if(t == false) {
        		  sendEmailService.sendEmail( "yahiabourguiba1997@gmail.com" ,  "Signature Fraudé" , file);
        	  }
        	  
        	 // sendEmailService.sendEmail( "yahiabourguiba1997@gmail.com" ,  "Signature Fraudé" , file);
        	 /* if(temp.toString() == "False") {
        		  System.out.println(temp);
      			sendEmailService.sendEmail( "yahiabourguiba1997@gmail.com" ,  "Signature Fraudé" , file);
        	  }*/
              //System.out.println(temp);
          }
          int n = 0;
          while(n != -1)
          {
          n = is.read();
          System.out.print(n);
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}
