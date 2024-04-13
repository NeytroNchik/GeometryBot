package Ru.vkBot;
import java.util.*;
import com.vk.api.sdk.exceptions.ApiParamException;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import com.vk.api.sdk.exceptions.ApiMessagesDenySendException;
import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;

@SuppressWarnings("ALL")

public class Botik {

    public static String figure = "unknown";                                            // Переменная, меняющая своё значение в зависимости от фигуры, с которой идёт работа
    public static String nameOfFigure = "not definded";                                // Переменная, меняющая своё значение в зависимости от названия точек фигуры
    public static boolean begin = false;                                              // Переменная, меняющая своё значение в зависимости от того, была ли нажата кнопка "Начать"
    public static boolean namedCorrectly = false;                                    // Переменная, меняющая своё значение в зависимости от того, правильно ли названа фигура
    public static boolean isFigureWithCorrectlyName = false;                        // Переменная, меняющая своё значение в зависимости от того, идёт ли сейчас работа с какой-либо фигурой и корректна ли она названа
    public static boolean isTrapezoid = false;                                     // Переменная, меняющая своё значение в зависимости от того, нажата ли была кнопка "Трапеция"
    public static boolean isTrangle = false;                                      // Переменная, меняющая своё значение в зависимости от того, нажата ли была кнопка "Треугольник"
    public static boolean wasTypeChoice = false;                                 // Переменная, меняющая своё значение в зависимости от того, был ли выбран вид той или иной фигуры
    public static boolean isBack = false;                                       // Переменная, меняющая своё значение в зависимости от того, нажата ли была кнопка "Назад"
    public static int value;                                                   // Переменная, меняющая своё значение в зависимости от значения стороны фигуры
    public static String valueStr = "no";                                     // Переменная, меняющая своё значение в зависимости от значения стороны фигуры, переведённого в строчный тип
    public static int square = 0;                                            // Переменная, меняющая своё значение в зависимости от площади фигуры
    public static String squareToString = "no value";                       // Переменная, меняющая своё значение в зависимости от площади фигуры в строчном типе

    public static void main(String[] args) throws ClientException, ApiException, InterruptedException, ApiMessagesDenySendException, ApiParamException {

        TransportClient transportClient = new HttpTransportClient(); //?
        VkApiClient vk = new VkApiClient(transportClient); //?
        Random random = new Random(); //?
        GroupActor actor = new GroupActor(212819492, "3b7d814917ac7e308d2d618338093b420498fbda28c939d8fa5c8e548700518d1ea701d5ea82a6653bc4e"); // Обозначени ключа доступа и айди паблика с ботом
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs(); //?

        Keyboard keyboardHello = new Keyboard();                        // Клавиатура с "Начать"
        Keyboard keyboard = new Keyboard();                            // Клавиатура с фигурами
        Keyboard keyboardTrapezoid = new Keyboard();                  // Клавиатура с трапециями
        Keyboard keyboardTrangle = new Keyboard();                   // Клавиатура с треугольниками
        Keyboard keyboardNeedToFindInSquare = new Keyboard();       // Клавиатура с тем, что нужно найти в квадрате
        Keyboard keyboardEmpty = new Keyboard();

        List<List<KeyboardButton>> allKey0 = new ArrayList<>();          // Массив массивов с кнопкой "Начать"
        List<List<KeyboardButton>> allKey = new ArrayList<>();          // Массив массивов с кнопками фигур
        List<List<KeyboardButton>> allKey1 = new ArrayList<>();        // Массив массивов с кнопками трапеции
        List<List<KeyboardButton>> allKey2 = new ArrayList<>();       // Массив массивов с кнопками треугольника
        List<List<KeyboardButton>> allKey3 = new ArrayList<>();      // Массив массивов с кнопками того, что нужно найти в квадрате

        List<KeyboardButton> line0 = new ArrayList<>();                             //
        List<KeyboardButton> line1 = new ArrayList<>();                            //
        List<KeyboardButton> line2 = new ArrayList<>();                           //
        List<KeyboardButton> line3 = new ArrayList<>();                          //
        List<KeyboardButton> line4 = new ArrayList<>();                         //
        List<KeyboardButton> line5 = new ArrayList<>();                        //
        List<KeyboardButton> line6 = new ArrayList<>();                       // МАССИВЫ С КНОПКАМИ
        List<KeyboardButton> line7 = new ArrayList<>();                      //
        List<KeyboardButton> line8 = new ArrayList<>();                     //
        List<KeyboardButton> line9 = new ArrayList<>();                    //
        List<KeyboardButton> line10 = new ArrayList<>();                  //
        List<KeyboardButton> line11 = new ArrayList<>();                 //
        List<KeyboardButton> line12 = new ArrayList<>();                //
        List<KeyboardButton> line13 = new ArrayList<>();               //
        List<KeyboardButton> line14 = new ArrayList<>();              //
        List<KeyboardButton> line15 = new ArrayList<>();             //
        List<KeyboardButton> line16 = new ArrayList<>();            //
        List<KeyboardButton> line17 = new ArrayList<>();           //
        List<KeyboardButton> line18 = new ArrayList<>();          //


        //PRIMARY - белый, POSITIVE - зелёный, NEGATIVE - красный, DEFAULT - чёрный
        line0.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Начать").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.NEGATIVE));                          //
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Квадрат").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.NEGATIVE));                        //
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Треугольник").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));                    //
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Прямоугольник").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));                //
        line4.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Параллелограмм").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));              //
        line5.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Трапеция").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));                    //
        line6.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Ромб").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.NEGATIVE));                      //
        line7.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Произвольная").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.NEGATIVE));             // ВНЕШНИЙ ВИД КНОПОК
        line8.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Равнобедренная").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));          //
        line9.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Прямоугольная").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));           //
        line10.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Произвольный").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.NEGATIVE));         //
        line11.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Равнобедренный").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));      //
        line12.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Прямоугольный").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.PRIMARY));       //
        line13.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Назад").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));              //
        line14.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Назад").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));             //
        line15.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Площадь").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));          //
        line16.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Периметр").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));        //
        line17.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Диагональ").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));      //
        line18.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Назад").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.DEFAULT));         //


        allKey0.add(line0);                                                           //
        allKey.add(line1);                                                           //
        allKey.add(line2);                                                          //
        allKey.add(line3);                                                         //
        allKey.add(line4);                                                        //
        allKey.add(line5);                                                       //
        allKey.add(line6);                                                      //
        allKey1.add(line7);                                                    //
        allKey1.add(line8);                                                   // ДОБАВЛЕНИЕ КНОПОК В ОДИН МАССИВ
        allKey1.add(line9);                                                  //
        allKey1.add(line13);                                                //
        allKey2.add(line10);                                               //
        allKey2.add(line11);                                              //
        allKey2.add(line12);                                             //
        allKey2.add(line14);                                            //
        allKey3.add(line15);                                           //
        allKey3.add(line16);                                          //
        allKey3.add(line17);                                         //
        allKey3.add(line18);                                        //
                                                                   //
        keyboardHello.setButtons(allKey0);                        //
        keyboard.setButtons(allKey);                             //
        keyboardTrapezoid.setButtons(allKey1);                  //
        keyboardTrangle.setButtons(allKey2);                   //
        keyboardNeedToFindInSquare.setButtons(allKey3);       //

        while (true) {                          // Бесконечный цикл для отправки сообщений

            MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);   // Возвращает обновления личных сообщений пользователя
            List<Message> messages = historyQuery.execute().getMessages().getItems();                       // Массив полученных сообщений

            if (!messages.isEmpty()) {                              // Если сообщение не пустое
                messages.forEach (message -> {                     // Переменная message проходит по всему массиву messages
                    System.out.println(message.toString());       // Вывод в консоль

                    try {

                        int messageId = message.getId();                                     // АЙДИ СООБЩЕНИЯ
                        String derived = message.getText();                                 // ПОЛУЧЕННОЕ СООБЩЕНИЕ
                        char[] derivedCharArray = derived.toUpperCase().toCharArray();     // МАССИВ СИМВОЛОВ ПОЛУЧЕННОГО СООБЩЕНИЯ
                        char[] nameArray = nameOfFigure.toCharArray();                    // МАССИВ СИМВОЛОВ НАЗВАНИЯ ТОЧЕК ФИГУРЫ

                        if (derived.equals("Начать")) {                                                    // Самая первая кнопка, не нажав которую, пользователь не сожет работать с ботом

                            begin = true;
                            vk.messages().send(actor)                                                    // Метод для отправки сообщений ботом
                                    .message("Выберите фигуру, с которой предстоит работать: ")         // Состав этого сообщения
                                    .userId(message.getFromId())                                       // Кому именно будет отправлено сообщение
                                    .randomId(random.nextInt(10000))                            // Уникальный идентификатор предназначенный для того, чтобы избежать повторной отправки сообщения.
                                    .keyboard(keyboard)                                               // Вывод клавиатуры (в скобках указывается конкретная клавиатура, которую нужно вывести
                                    .execute();                                                      // Выполнение всех предыдущих команд
                        }

                        if (begin == true) {

                            if (((derived.toUpperCase().matches("^[A-Z][A-Z][A-Z][A-Z]$")) && isTrangle == false) ||

                                    (derived.toUpperCase().matches("^[A-Z][A-Z][A-Z]$") && isTrangle == true))          // Если фигура названа правильно (4 или 3 идущих подряд латинских символа)
                                 namedCorrectly = true;

                            else
                                namedCorrectly = false;       // Если фигура названа неправильно

                            if ((namedCorrectly == false) && (figure.equals("square") || figure.equals("rectangle") || figure.equals("parallelogram")  ||
                                    figure.equals("rhomb") || figure.equals("arbitraryTrapezoid") || figure.equals("isoscelesTrapezoid") ||
                                    figure.equals("rectangularTrapezoid") || figure.equals("abitrarTrangle") || figure.equals("isoscelesTrangle") ||
                                    figure.equals("rectangularTrangle")))

                             isFigureWithCorrectlyName = false;      // Если идёт работа с фигурой и её название неправильное

                            else
                                isFigureWithCorrectlyName = true;

                       if (derived.equals("Квадрат")) {                            // Определение сторон КВАДРАТА

                           isFigureWithCorrectlyName = true;
                           figure = "square";
                            vk.messages().send(actor)
                                    .message("Введите названия точек вашего квадрата, начиная от нижней левой вершины и по часовой стрелке: ")
                                    .attachment("photo-212819492_457239018")     //Фото расположения точек КВАДРАТА
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .keyboard(keyboard)
                                    .execute();
                        }

                        else if (derived.equals("Прямоугольник")) {              // Определение сторон ПРЯМОУГОЛЬНИКА

                           isFigureWithCorrectlyName = true;
                           figure = "rectangle";
                            vk.messages().send(actor)
                                    .message("Введите названия точек вашего прямоугольника, начиная от нижней левой вершины и по часовой стрелке: ")
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();

                            vk.messages().send(actor)
                                    .attachment("photo-212819492_457239019")     //Фото расположения точек ПРЯМОУГОЛЬНИКА
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();

                        }

                        else if (derived.equals("Параллелограмм")) {             // Определение сторон ПАРАЛЛЕЛОГРАММА

                           isFigureWithCorrectlyName = true;
                           figure = "parallelogram";
                            vk.messages().send(actor)
                                    .message("Введите названия точек вашего параллелограмма, начиная от нижней левой вершины и по часовой стрелке: ")
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();

                            vk.messages().send(actor)
                                    .attachment("photo-212819492_457239020")     //Фото расположения точек ПАРАЛЛЕЛОГРАММА
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }

                        else if (derived.equals("Трапеция")) {                     // Определение вида ТРАПЕЦИИ

                            figure = "trapezoid";
                            isTrapezoid=true;
                            vk.messages().send(actor)
                                    .message("Выберите вид вашей трапеции:")
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .keyboard(keyboardTrapezoid)
                                    .execute();
                        }

                        else if (derived.equals("Треугольник")) {                            // Определение вида ТРЕУГОЛЬНИКА

                           isFigureWithCorrectlyName = true;
                           figure = "trangle";
                           isTrangle = true;
                           vk.messages().send(actor)
                                   .message("Выберите вид вашего треугольника (если ваш треугольник равнобедренный и прямоугольный одновременно, то нажмите кнопку \"Прямоугольный\", затем в следующем пункте укажите какие из сторон равны): ")
                                   .userId(message.getFromId())
                                   .randomId(random.nextInt(10000))
                                   .keyboard(keyboardTrangle)
                                   .execute();
                       }

                       else if (derived.equals("Ромб")) {             // Определение сторон РОМБА

                           isFigureWithCorrectlyName = true;
                           figure = "rhomb";
                           vk.messages().send(actor)
                                   .message("Введите названия точек вашего ромба, начиная от нижней левой вершины и по часовой стрелке: ")
                                   .userId(message.getFromId())
                                   .randomId(random.nextInt(10000))
                                   .execute();

                           vk.messages().send(actor)
                                   .attachment("photo-212819492_457239027")     //Фото расположения точек РОМБА
                                   .userId(message.getFromId())
                                   .randomId(random.nextInt(10000))
                                   .execute();
                       }

                        else if (derived.equals("Назад")) {                     // Обратно - в меню с фигурами

                            figure = "unknown";
                            isTrangle = false;
                            isBack = true;
                            isFigureWithCorrectlyName = true;
                            vk.messages().send(actor)
                                   .message("Выберите фигуру, с которой предстоит работать:")
                                   .userId(message.getFromId())
                                   .randomId(random.nextInt(10000))
                                   .keyboard(keyboard)
                                   .execute();
                       }

                        if (isTrapezoid == true) {

                        if (derived.equals("Произвольная")) {                     // ПРОИЗВОЛЬНАЯ ТРАПЕЦИЯ и определение её сторон

                            isFigureWithCorrectlyName = true;
                            figure = "arbitraryTrapezoid";
                            vk.messages().send(actor)
                                    .message("Введите названия точек вашей произвольной трапеции, начиная от нижней левой вершины и по часовой стрелке: ")
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();

                            vk.messages().send(actor)
                                    .attachment("photo-212819492_457239021")     // Фото расположения точек ПРОИЗВОЛЬНОЙ ТРАПЕЦИИ
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }

                        else if (derived.equals("Равнобедренная")) {                     // РАВНОБЕДРЕННАЯ ТРАПЕЦИЯ и определение её сторон

                            figure = "isoscelesTrapezoid";
                            vk.messages().send(actor)
                                    .message("Введите названия точек вашей равнобедренной трапеции, начиная от нижней левой вершины и по часовой стрелке: ")
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();

                            vk.messages().send(actor)
                                    .attachment("photo-212819492_457239022")     // Фото расположения точек РАВНОБЕДРЕННОЙ ТРАПЕЦИИ
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }

                        else if (derived.equals("Прямоугольная")) {                     // ПРЯМОУГОЛЬНАЯ ТРАПЕЦИЯ и определение её сторон

                            isFigureWithCorrectlyName = true;
                            figure = "rectangularTrapezoid";
                            vk.messages().send(actor)
                                    .message("Введите названия точек вашей прямоугольной трапеции, начиная от нижней левой вершины и по часовой стрелке: ")
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();

                            vk.messages().send(actor)
                                    .attachment("photo-212819492_457239023")     // Фото расположения точек ПРЯМОУГОЛЬНОЙ ТРАПЕЦИИ
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }
                        }

                            if (isTrangle == true) {

                                if (derived.equals("Произвольный")) {                     // ПРОИЗВОЛЬНЫЙ ТРЕУГОЛЬНИК и определение его сторон

                                    isFigureWithCorrectlyName = true;
                                    figure = "abitrarTrangle";
                                    vk.messages().send(actor)
                                            .message("Введите названия точек вашего произвольного треугольника, начиная от нижней левой вершины и по часовой стрелке: ")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();

                                    vk.messages().send(actor)
                                            .attachment("photo-212819492_457239024")     // Фото расположения точек ПРОИЗВОЛЬНОГО ТРЕУГОЛЬНИКА
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }

                                else if (derived.equals("Равнобедренный")) {                     // РАВНОБЕДРЕННЫЙ ТРЕУГОЛЬНИК и определение его сторон

                                    isFigureWithCorrectlyName = true;
                                    figure = "isoscelesTrangle";
                                    vk.messages().send(actor)
                                            .message("Введите названия точек вашего равнобедренного треугольника, начиная от нижней левой вершины и по часовой стрелке: ")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();

                                    vk.messages().send(actor)
                                            .attachment("photo-212819492_457239026")     // Фото расположения точек РАВНОБЕДРЕННОГО ТРЕУГОЛЬНИКА
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }

                                else if (derived.equals("Прямоугольный")) {                     // ПРЯМОУГОЛЬНЫЙ ТРЕУГОЛЬНИК и определение его сторон

                                    isFigureWithCorrectlyName = true;
                                    figure = "retrangularTrangle";
                                    vk.messages().send(actor)
                                            .message("Введите названия точек вашего прямоугольного треугольника, начиная от нижней левой вершины и по часовой стрелке: ")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();

                                    vk.messages().send(actor)
                                            .attachment("photo-212819492_457239025")     // Фото расположения точек ПРЯМОУГОЛЬНОГО ТРЕУГОЛЬНИКА
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }

                                else if (derived.toUpperCase().matches("^[A-Z][A-Z][A-Z]$")) {

                                    if (derivedCharArray[0] == derivedCharArray[1] || derivedCharArray[0] == derivedCharArray[2] || derivedCharArray[1] == derivedCharArray[2]) {

                                        vk.messages().send(actor)
                                                .message("Введите другие символы")
                                                .userId(message.getFromId())
                                                .randomId(random.nextInt(10000))
                                                .execute();
                                    }

                                    else if (figure.equals("abitrarTrangle")) {                    // Называние сторон ПРОИЗВОЛЬНОГО ТРЕУГОЛЬНИКА

                                        vk.messages().send(actor)
                                                .message("Ваша фигура - произвольный треугольник со сторонами " + derivedCharArray[0] + derivedCharArray[1] + ", " + derivedCharArray[1] + derivedCharArray[2] + ", " + derivedCharArray[2] + derivedCharArray[0] + ". Если вы выбрали неправильное название, просто напишите новое название для сторон.")
                                                .userId(message.getFromId())
                                                .randomId(random.nextInt(10000))
                                                .execute();

                                        nameOfFigure = derivedCharArray[0] + "" + derivedCharArray[1] + "" + derivedCharArray[2];
                                        vk.messages().send(actor)
                                                .message("Какие из сторон вашего произвольного треугольника известны? (Название сторон писать латиницей. Например ab=4). ")
                                                .userId(message.getFromId())
                                                .randomId(random.nextInt(10000))
                                                .execute();
                                    }

                                    else if (figure.equals("isoscelesTrangle")) {                    // Называние сторон РАВНОБЕДРЕННОГО ТРЕУГОЛЬНИКА

                                        vk.messages().send(actor)
                                                .message("Ваша фигура - равнобедренный треугольник со сторонами " + derivedCharArray[0] + derivedCharArray[1] + ", " + derivedCharArray[1] + derivedCharArray[2] + ", " + derivedCharArray[2] + derivedCharArray[0] + ". Если вы выбрали неправильное название, просто напишите новое название для сторон.")
                                                .userId(message.getFromId())
                                                .randomId(random.nextInt(10000))
                                                .execute();

                                        nameOfFigure = derivedCharArray[0] + "" + derivedCharArray[1] + "" + derivedCharArray[2];
                                        vk.messages().send(actor)
                                                .message("Какие из сторон вашего равнобедренного треугольника известны? (Название сторон писать латиницей. Например ab=4). ")
                                                .userId(message.getFromId())
                                                .randomId(random.nextInt(10000))
                                                .execute();
                                    }

                                    else if (figure.equals("retrangularTrangle")) {                    // Называние сторон ПРЯМОУГОЛЬНОГО ТРЕУГОЛЬНИКА

                                        vk.messages().send(actor)
                                                .message("Ваша фигура - прямоугольный треугольник со сторонами " + derivedCharArray[0] + derivedCharArray[1] + ", " + derivedCharArray[1] + derivedCharArray[2] + ", " + derivedCharArray[2] + derivedCharArray[0] + ". Если вы выбрали неправильное название, просто напишите новое название для сторон.")
                                                .userId(message.getFromId())
                                                .randomId(random.nextInt(10000))
                                                .execute();

                                        nameOfFigure = derivedCharArray[0] + "" + derivedCharArray[1] + "" + derivedCharArray[2];
                                        vk.messages().send(actor)
                                                .message("Какие из сторон вашего прямоугольного треугольника известны? (Название сторон писать латиницей. Например ab=4). ")
                                                .userId(message.getFromId())
                                                .randomId(random.nextInt(10000))
                                                .execute();
                                    }
                                }
                            }

                        else if (derived.toUpperCase().matches("^[A-Z][A-Z][A-Z][A-Z]$")) {        // Называние сторон латиницей

                            // Проверка на повторяющиеся буквы
                            if ((figure.equals("square") || figure.equals("rectangle") || figure.equals("parallelogram") || figure.equals("rhomb") || figure.equals("arbitraryTrapezoid") || figure.equals("isoscelesTrapezoid") || figure.equals("rectangularTrapezoid")) && (derivedCharArray[0] == derivedCharArray[1] || derivedCharArray[0] == derivedCharArray[2] || derivedCharArray[0] == derivedCharArray[3] || derivedCharArray[1] == derivedCharArray[2] || derivedCharArray[1] == derivedCharArray[3] || derivedCharArray[2] == derivedCharArray[3])) {
                                vk.messages().send(actor)
                                        .message("Введите другие символы")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                            else if (figure.equals("square")) {                    // Называние сторон КВАДРАТА

                                vk.messages().send(actor)
                                        .message("Ваша фигура - квадрат со сторонами " + derivedCharArray[0] + derivedCharArray[1] + ", " + derivedCharArray[1] + derivedCharArray[2] + ", " + derivedCharArray[2] + derivedCharArray[3] + ", " + derivedCharArray[3] + derivedCharArray[0] + ". Если вы выбрали неправильное название, просто напишите новое название для сторон.")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();

                                nameOfFigure = derivedCharArray[0] + "" + derivedCharArray[1] + "" + derivedCharArray[2] + "" + derivedCharArray[3];
                                vk.messages().send(actor)
                                        .message("Какие из сторон вашего квадрата известны? (Название сторон писать латиницей. Например ab=4). ")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                           else if (figure.equals("rectangle")) {                  // Называние сторон ПРЯМОУГОЛЬНИКА

                                vk.messages().send(actor)
                                        .message("Ваша фигура - прямоугольник со сторонами " + derivedCharArray[0] + derivedCharArray[1] + ", " + derivedCharArray[1] + derivedCharArray[2] + ", " + derivedCharArray[2] + derivedCharArray[3] + ", " + derivedCharArray[3] + derivedCharArray[0] + ". Если вы выбрали неправильное название, просто напишите новое название для сторон.")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();

                                nameOfFigure = derivedCharArray[0] + "" + derivedCharArray[1] + "" + derivedCharArray[2] + "" + derivedCharArray[3];vk.messages().send(actor)
                                        .message("Какие из сторон вашего прямоугольника известны? (Название сторон писать латиницей. Например ab=4). ")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                            else if (figure.equals("parallelogram")) {             // Называние сторон ПАРАЛЛЕЛОГРАММА

                                vk.messages().send(actor)
                                        .message("Ваша фигура - параллелограмм со сторонами " + derivedCharArray[0] + derivedCharArray[1] + ", " + derivedCharArray[1] + derivedCharArray[2] + ", " + derivedCharArray[2] + derivedCharArray[3] + ", " + derivedCharArray[3] + derivedCharArray[0] + ". Если вы выбрали неправильное название, просто напишите новое название для сторон.")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();

                                nameOfFigure = derivedCharArray[0] + "" + derivedCharArray[1] + "" + derivedCharArray[2] + "" + derivedCharArray[3];vk.messages().send(actor);
                                vk.messages().send(actor)
                                        .message("Какие из сторон вашего параллелограмма известны? (Название сторон писать латиницей. Например ab=4). ")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                            else if (figure.equals("rhomb")) {                 // Называние сторон РОМБА

                                vk.messages().send(actor)
                                        .message("Ваша фигура - ромб со сторонами " + derivedCharArray[0] + derivedCharArray[1] + ", " + derivedCharArray[1] + derivedCharArray[2] + ", " + derivedCharArray[2] + derivedCharArray[3] + ", " + derivedCharArray[3] + derivedCharArray[0] + ". Если вы выбрали неправильное название, просто напишите новое название для сторон.")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();

                                nameOfFigure = derivedCharArray[0] + "" + derivedCharArray[1] + "" + derivedCharArray[2] + "" + derivedCharArray[3];vk.messages().send(actor);
                                vk.messages().send(actor)
                                        .message("Какие из сторон вашего ромба известны? (Название сторон писать латиницей. Например ab=4). ")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                           else if (figure.equals("arbitraryTrapezoid")) {          // Называние сторон ПРОИЗВОЛЬНОЙ ТРАПЕЦИИ

                                vk.messages().send(actor)
                                        .message("Ваша фигура - произвольная трапеция со сторонами " + derivedCharArray[0] + derivedCharArray[1] + ", " + derivedCharArray[1] + derivedCharArray[2] + ", " + derivedCharArray[2] + derivedCharArray[3] + ", " + derivedCharArray[3] + derivedCharArray[0] + ". Если вы выбрали неправильное название, просто напишите новое название для сторон.")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();

                                nameOfFigure = derivedCharArray[0] + "" + derivedCharArray[1] + "" + derivedCharArray[2] + "" + derivedCharArray[3];
                                vk.messages().send(actor)
                                        .message("Какие из сторон вашей произвольной трапеции известны? (Название сторон писать латиницей. Например, ab=4). ")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                            else if (figure.equals("isoscelesTrapezoid")) {          // Называние сторон РАВНОБЕДРЕННОЙ ТРАПЕЦИИ

                                vk.messages().send(actor)
                                        .message("Ваша фигура - равнобедренная трапеция со сторонами " + derivedCharArray[0] + derivedCharArray[1] + ", " + derivedCharArray[1] + derivedCharArray[2] + ", " + derivedCharArray[2] + derivedCharArray[3] + ", " + derivedCharArray[3] + derivedCharArray[0] + ". Если вы выбрали неправильное название, просто напишите новое название для сторон.")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();

                                nameOfFigure = derivedCharArray[0] + "" + derivedCharArray[1] + "" + derivedCharArray[2] + "" + derivedCharArray[3];
                                vk.messages().send(actor)
                                        .message("Какие из сторон вашей равнобедренной трапеции известны? (Название сторон писать латиницей. Например ab=4). ")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                            else if (figure.equals("rectangularTrapezoid")) {          // Называние сторон ПРЯМОУГОЛЬНОЙ ТРАПЕЦИИ

                                vk.messages().send(actor)
                                        .message("Ваша фигура - прямоугольная трапеция со сторонами " + derivedCharArray[0] + derivedCharArray[1] + ", " + derivedCharArray[1] + derivedCharArray[2] + ", " + derivedCharArray[2] + derivedCharArray[3] + ", " + derivedCharArray[3] + derivedCharArray[0] + ". Если вы выбрали неправильное название, просто напишите новое название для сторон.")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();

                                nameOfFigure = derivedCharArray[0] + "" + derivedCharArray[1] + "" + derivedCharArray[2] + "" + derivedCharArray[3];
                                vk.messages().send(actor)
                                        .message("Какие из сторон вашей прямоугольной трапеции известны? (Название сторон писать латиницей. Например ab=4). ")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                        } // Конец проверки на латинские буквы

                            else if (derived.toUpperCase().matches("^[A-Z][A-Z]*=*[1-9]$") && (figure.equals("square"))) {

                                isFigureWithCorrectlyName = true;

                                if ((derivedCharArray[0] + derivedCharArray[1]) != (nameArray[0]+nameArray[1])) {

                                    vk.messages().send(actor)
                                            .message("У вашего квадрата нет такой стороны.")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();


                                 if (derivedCharArray[3] == '0') {

                                    isFigureWithCorrectlyName = true;

                                    vk.messages().send(actor)
                                            .message("Это геометрически невозможно")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }
                            }
                                else {
                                    value = derivedCharArray[3] - '0';
                                    derived.toUpperCase();
                                    vk.messages().send(actor)
                                            .message(derived)
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();

                                    vk.messages().send(actor)
                                            .message("Что нужно найти в вашем квадрате?")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .keyboard(keyboardNeedToFindInSquare)
                                            .execute();
                                }
                            }

                            else if (derived.toUpperCase().matches("^[A-Z][A-Z]*=*[1-9][1-9]$") && (figure.equals("square"))){

                                isFigureWithCorrectlyName = true;

                                if ((derivedCharArray[0] + derivedCharArray[1]) != (nameArray[0]+nameArray[1])) {

                                    vk.messages().send(actor)
                                            .message("У вашего квадрата нет такой стороны.")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }

                                else if ((derivedCharArray[3] == '0') || ((derivedCharArray[3] == '0') && derivedCharArray[4] == '0')) {

                                    vk.messages().send(actor)
                                            .message("Это математически невозможно.")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }

                                else {

                                    valueStr = (derivedCharArray[3] + "") + (derivedCharArray[4] + "");
                                    value = Integer.parseInt(valueStr);
                                    System.out.println(value);
                                    derived.toUpperCase();
                                    vk.messages().send(actor)
                                            .message(derived)
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();

                                    vk.messages().send(actor)
                                            .message("Что нужно найти в вашем квадрате?")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .keyboard(keyboardNeedToFindInSquare)
                                            .execute();
                                }
                            }



                            else if (derived.toUpperCase().matches("^[A-Z][A-Z]*=*[1-9][1-9][1-9]$") && (figure.equals("square"))) {

                                isFigureWithCorrectlyName = true;

                                if ((derivedCharArray[0] + derivedCharArray[1]) != (nameArray[0]+nameArray[1])) {

                                    vk.messages().send(actor)
                                            .message("У вашего квадрата нет такой стороны.")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .keyboard(keyboardNeedToFindInSquare)
                                            .execute();
                                }

                                else if ((derivedCharArray[3] == '0') || ((derivedCharArray[3] == '0') && (derivedCharArray[4] == '0') && (derivedCharArray[5] == '0'))) {

                                    vk.messages().send(actor)
                                            .message("Это математически невозможно.")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }

                                else {

                                    valueStr = (derivedCharArray[3] + "") + (derivedCharArray[4] + "") + (derivedCharArray[5] + "");
                                    value = Integer.parseInt(valueStr);
                                    System.out.println(value);
                                    derived.toUpperCase();
                                    vk.messages().send(actor)
                                            .message(derived)
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();

                                    vk.messages().send(actor)
                                            .message("Что нужно найти в вашем квадрате?")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .keyboard(keyboardNeedToFindInSquare)
                                            .execute();
                                }
                            }

                            else if (derived.toUpperCase().matches("^[A-Z][A-Z]*=*[1-9][1-9][1-9][1-9]$") && (figure.equals("square"))) {

                                isFigureWithCorrectlyName = true;

                                if ((derivedCharArray[0] + derivedCharArray[1]) != (nameArray[0]+nameArray[1])) {

                                    vk.messages().send(actor)
                                            .message("У вашего квадрата нет такой стороны.")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }

                                else if ((derivedCharArray[3] == '0') || ((derivedCharArray[3] == '0') && (derivedCharArray[4] == '0') && (derivedCharArray[5] == '0') && (derivedCharArray[6] == '0'))) {

                                    vk.messages().send(actor)
                                            .message("Это математически невозможно.")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }

                                else {

                                    valueStr = (derivedCharArray[3] + "") + (derivedCharArray[4] + "") + (derivedCharArray[5] + "") + (derivedCharArray[6] + "");
                                    value = Integer.parseInt(valueStr);
                                    System.out.println(value);
                                    derived.toUpperCase();
                                    vk.messages().send(actor)
                                            .message(derived)
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();

                                    vk.messages().send(actor)
                                            .message("Что нужно найти в вашем квадрате? (Максимальное значение для нахождения площади - 46340)")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .keyboard(keyboardNeedToFindInSquare)
                                            .execute();
                                }
                            }

                            else if (derived.toUpperCase().matches("^[A-Z][A-Z]*=*[1-9][1-9][1-9][1-9][1-9]$") && (figure.equals("square"))) {

                                isFigureWithCorrectlyName = true;

                                if ((derivedCharArray[0] + derivedCharArray[1]) != (nameArray[0]+nameArray[1])) {

                                    vk.messages().send(actor)
                                            .message("У вашего квадрата нет такой стороны.")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }

                                else if ((derivedCharArray[2] == '0') || ((derivedCharArray[3] == '0') && (derivedCharArray[4] == '0') && (derivedCharArray[5] == '0') && (derivedCharArray[6] == '0') && (derivedCharArray[7] == '0'))) {

                                    vk.messages().send(actor)
                                            .message("Это математически невозможно.")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }

                                else {

                                    valueStr = (derivedCharArray[3] + "") + (derivedCharArray[4] + "") + (derivedCharArray[5] + "") + (derivedCharArray[6] + "") + (derivedCharArray[7] + "");
                                    value = Integer.parseInt(valueStr);
                                    System.out.println(value);
                                    derived.toUpperCase();
                                    vk.messages().send(actor)
                                            .message(derived)
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();

                                    vk.messages().send(actor)
                                            .message("Что нужно найти в вашем квадрате?")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .execute();
                                }
                            }

                            else if (derived.equals("Площадь")){

                                isFigureWithCorrectlyName = true;
                                square = value*value;
                                squareToString = String.valueOf(square);
                                vk.messages().send(actor)
                                        .message(squareToString)
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                            if (isFigureWithCorrectlyName == false) {         // Если в названии допущена ошибка

                                vk.messages().send(actor)
                                        .message("Введите другие символы")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                            if (isFigureWithCorrectlyName == false) {         // Если в названии допущена ошибка

                                vk.messages().send(actor)
                                        .message("Введите другие символы")
                                        .userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            }

                        } // Конец проверки на нажатие кнопки "Начать"

                        else if (begin == false) {

                            vk.messages().send(actor)
                                    .message("Нажмите кнопку \"Начать\" или напишите слово \"Начать\"")      // Если не нажата кнопка "Начать"
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .keyboard(keyboardHello)
                                    .execute();
                        }

                        System.out.println(figure);            // С какой фигурой сейчас идёт работа
                        System.out.println(begin);            // Была ли нажата кнопка "Начать"
                        System.out.println(namedCorrectly);  // Если фигура правильно названа

                    } // Конец обработки исключений

                    catch (ApiException | ClientException e) {  e.printStackTrace(); }    // Исключения

                                                                                                        }); // Конец цикла переменной message

                                                                                                    } // Конец цикла "Если пришло сообщение"
            ts = vk.messages().getLongPollServer(actor).execute().getTs();        //?
            Thread.sleep(500);                                              //?

                }     //Конец бесконечного цикла для отправки сообщений
    }   // Конец главного класса

}    //Конец класса

