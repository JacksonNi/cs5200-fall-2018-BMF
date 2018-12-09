package edu.northeastern.cs5200;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Player;
import edu.northeastern.cs5200.repositories.AddressRepository;
import edu.northeastern.cs5200.repositories.PlayerRepository;

@SpringBootApplication
public class Cs5200Fall2018BmfApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cs5200Fall2018BmfApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner runner(SportraderWebServiceClient client) {
//		return args -> {
////			Faculty mark = new Faculty();
////			mark.setFullName("Mark");
////			mark.setUsername("mark");
////			mark.setPassword("iammark");
////			mark.setEmail("mark@soccer.eu");
////			mark.setDob("1986-06-06");
////			Faculty jack = new Faculty();
////			jack.setFullName("Jack");
////			jack.setUsername("jack");
////			jack.setPassword("iamjack");
////			jack.setEmail("jack@soccer.eu");
////			jack.setDob("1890-10-10");
////			int[] tids = {7,8,17,23};
////			for (int tid: tids) {
////				if (tid < 10) {
////					client.mapTournamentData(jack, tid);				
////				} else {
////					client.mapTournamentData(mark, tid);
////				}
////			}
////			client.mapAllClubData();
//			
////			client.mapSeasonMatch();
////			client.mapAllMatchesNewsData();
//			client.mapClubSeasonData();
//			
//		};
//	}
	
}
