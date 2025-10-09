package gruppe6.kea.kinobackend.Reservation.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import gruppe6.kea.kinobackend.DTO.TicketDTO;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public byte[] generateTicketPdfBytes(TicketDTO dto) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("Kino XP Billet"));
            document.add(new Paragraph("Navn På Reservation: " + dto.getName()));
            document.add(new Paragraph("Biograf: " + dto.getCinemaName()));
            document.add(new Paragraph("Sal: " + dto.getTheatreName()));
            document.add(new Paragraph("Film: " + dto.getMovieTitle()));
            document.add(new Paragraph("Dag: " + dto.getShowTime()
                    .format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy", new Locale("da", "DK")))));

            document.add(new Paragraph("Tid: " + dto.getShowTime()
                    .format(DateTimeFormatter.ofPattern("HH:mm"))));
            document.add(new Paragraph("Sæde: " + dto.getSeats().toString())); // kun ét sæde pr. PDF
            document.add(new Paragraph("Tak for din reservation!"));

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Kunne ikke generere PDF", e);
        }
    }


    public void sendTicketMail(String to, List<byte[]> pdfBytes) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Din biografbillet 🎟️ - Gorilla kino");
            helper.setText("Her er din billet til Gorilla kino. God fornøjelse!");
            helper.setFrom("billet@kino-xp.dk");
            int count = 1;
            for (byte[] ticket : pdfBytes){
                helper.addAttachment("billet" + count +".pdf", new ByteArrayResource(ticket));
                count++;
            }


            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Kunne ikke sende mail", e);
        }
    }
}
