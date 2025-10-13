package gruppe6.kea.kinobackend.Reservation.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import gruppe6.kea.kinobackend.DTO.TicketDTO;
import gruppe6.kea.kinobackend.Models.Reservation;
import gruppe6.kea.kinobackend.Models.Ticket;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

            try {
                Image moviePoster = Image.getInstance(dto.getImagePoster());
                Image logo = Image.getInstance("src/main/resources/static/logo.png"); // path to your image
                logo.scaleToFit(150, 150);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);
                moviePoster.scaleToFit(350, 350);
                moviePoster.setAlignment(Image.ALIGN_LEFT + 30);
                document.add(moviePoster);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for(int i = 0; i<3; i++){
                document.add(new Paragraph(" "));
            }

// Fonts
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.DARK_GRAY);
            Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
            Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12, BaseColor.GRAY);

// Header
            Paragraph header = new Paragraph("Kino XP Billet", headerFont);
            header.setAlignment(Element.ALIGN_CENTER);
            header.setSpacingAfter(20f); // space after header
            document.add(header);

// Ticket details
            Paragraph name = new Paragraph();
            name.add(new Chunk("Navn På Reservation: ", labelFont));
            name.add(new Chunk(dto.getName(), valueFont));
            name.setSpacingAfter(5f);
            document.add(name);

            Paragraph cinema = new Paragraph();
            cinema.add(new Chunk("Biograf: ", labelFont));
            cinema.add(new Chunk(dto.getCinemaName(), valueFont));
            cinema.setSpacingAfter(5f);
            document.add(cinema);

            Paragraph theatre = new Paragraph();
            theatre.add(new Chunk("Sal: ", labelFont));
            theatre.add(new Chunk(dto.getTheatreName(), valueFont));
            theatre.setSpacingAfter(5f);
            document.add(theatre);

            Paragraph movie = new Paragraph();
            movie.add(new Chunk("Film: ", labelFont));
            movie.add(new Chunk(dto.getMovieTitle(), valueFont));
            movie.setSpacingAfter(5f);
            document.add(movie);

            Paragraph date = new Paragraph();
            date.add(new Chunk("Dag: ", labelFont));
            date.add(new Chunk(dto.getShowTime().format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy", new Locale("da", "DK"))), valueFont));
            date.setSpacingAfter(5f);
            document.add(date);

            Paragraph time = new Paragraph();
            time.add(new Chunk("Tid: ", labelFont));
            time.add(new Chunk(dto.getShowTime().format(DateTimeFormatter.ofPattern("HH:mm")), valueFont));
            time.setSpacingAfter(5f);
            document.add(time);

            Paragraph seats = new Paragraph();
            seats.add(new Chunk("Sæde: ", labelFont));
            seats.add(new Chunk(dto.getSeats().toString(), valueFont));
            seats.setSpacingAfter(20f);
            document.add(seats);

// Footer
            Paragraph footer = new Paragraph("Tak for din reservation!", footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);


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
            for (byte[] ticket : pdfBytes) {
                helper.addAttachment("billet" + count + ".pdf", new ByteArrayResource(ticket));
                count++;
            }

            System.out.println("mail sent");
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Kunne ikke sende mail", e);
        }
    }


    @Async
    public void sendTicksThroughMail(Reservation savedReservation){
        //EMAIL FIS
        List<byte[]> pdfBytesList = new ArrayList<>();
        // 6. Generer TicketDTO med Seat-objekter
        for (Ticket ticket : savedReservation.getTicketList()) {
            // Generer TicketDTO med ét sæde
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setName(savedReservation.getName());
            ticketDTO.setEmail(savedReservation.getEmail());
            ticketDTO.setMovieTitle(savedReservation.getShow().getMovie().getTitle());
            ticketDTO.setShowTime(savedReservation.getShow().getShowTime());
            ticketDTO.setTheatreName(savedReservation.getShow().getTheatre().getName());
            ticketDTO.setCinemaName(savedReservation.getShow().getTheatre().getCinema().getName());
            ticketDTO.setSeats(ticket.getBookedSeat().getSeat());
            ticketDTO.setImagePoster(ticket.getReservation().getShow().getMovie().getPosterImage());


            // 7. Generer PDF som byte-array og send mail
            byte[] pdfBytes = this.generateTicketPdfBytes(ticketDTO);
            pdfBytesList.add(pdfBytes);


        }
        this.sendTicketMail(savedReservation.getEmail(), pdfBytesList);
    }
}
