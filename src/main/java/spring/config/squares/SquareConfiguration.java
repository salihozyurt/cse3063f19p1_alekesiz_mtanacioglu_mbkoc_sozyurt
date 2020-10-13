package spring.config.squares;

import BL.squares.BSquare;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "spring.consumer")
public class SquareConfiguration {

    private static BSquare square;

    @Bean
    public BSquare getSquare() {
        return square;
    }

    public void setSquare(BSquare square) {
        SquareConfiguration.square = square;
    }
}
