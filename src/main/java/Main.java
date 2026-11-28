import ru.itis.noteshare.dao.ComposerDao;
import ru.itis.noteshare.models.ComposerModel;
import ru.itis.noteshare.service.ComposerService;
import ru.itis.noteshare.utils.DataConnection;

public class Main {
    public static void main(String[] args) {
        ComposerDao composerDao = new ComposerDao(DataConnection.getConnection());
        ComposerService composerService = new ComposerService(composerDao);

        composerService.saveComposer(ComposerModel.builder()
                .fullName("Вольфганг Амадей Моцарт")
                .country("Австрия")
                .build());

        composerService.saveComposer(ComposerModel.builder()
                .fullName("Людвиг ван Бетховен")
                .country("Германия")
                .build());

        composerService.saveComposer(ComposerModel.builder()
                .fullName("Антонио Вивальди")
                .country("Италия")
                .build());

        composerService.saveComposer(ComposerModel.builder()
                .fullName("Франц Шуберт")
                .country("Австрия")
                .build());

        composerService.saveComposer(ComposerModel.builder()
                .fullName("Георг Фридрих Гендель")
                .country("Германия")
                .build());

        composerService.saveComposer(ComposerModel.builder()
                .fullName("Ференц Лист")
                .country("Венгрия")
                .build());

        composerService.saveComposer(ComposerModel.builder()
                .fullName("Эдвард Григ")
                .country("Норвегия")
                .build());

        composerService.saveComposer(ComposerModel.builder()
                .fullName("Модест Мусоргский")
                .country("Россия")
                .build());

        composerService.saveComposer(ComposerModel.builder()
                .fullName("Николай Римский-Корсаков")
                .country("Россия")
                .build());

        composerService.saveComposer(ComposerModel.builder()
                .fullName("Сергей Прокофьев")
                .country("Россия")
                .build());
    }
}