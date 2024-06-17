-- MySQL dump 10.13  Distrib 8.4.0, for Linux (x86_64)
--
-- Host: localhost    Database: mizdooni_ca5
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `GlobalData`
--

DROP TABLE IF EXISTS `GlobalData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GlobalData` (
  `id` int NOT NULL,
  `loginnedUsername` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqob2xm9a2p9khxcjn8pg06yh4` (`loginnedUsername`),
  CONSTRAINT `FKqob2xm9a2p9khxcjn8pg06yh4` FOREIGN KEY (`loginnedUsername`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GlobalData`
--

LOCK TABLES `GlobalData` WRITE;
/*!40000 ALTER TABLE `GlobalData` DISABLE KEYS */;
INSERT INTO `GlobalData` VALUES (1,'MohammadJavad_Afsari','2.00Beta');
/*!40000 ALTER TABLE `GlobalData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservations`
--

DROP TABLE IF EXISTS `Reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Reservations` (
  `TABLE_NUMBER` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `reservationDateTime` datetime(6) DEFAULT NULL,
  `CLIENT_USERNAME` varchar(255) DEFAULT NULL,
  `RESTAURANT_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm067m1hetxf74klffi19nbwk1` (`CLIENT_USERNAME`),
  KEY `FK3gprh1a93fgm7rgpioyx4le2i` (`RESTAURANT_NAME`),
  KEY `FK5hhfj6suovld5squgo1susl81` (`TABLE_NUMBER`),
  CONSTRAINT `FK3gprh1a93fgm7rgpioyx4le2i` FOREIGN KEY (`RESTAURANT_NAME`) REFERENCES `Restaurants` (`name`),
  CONSTRAINT `FK5hhfj6suovld5squgo1susl81` FOREIGN KEY (`TABLE_NUMBER`) REFERENCES `Tables` (`tableNumber`),
  CONSTRAINT `FKm067m1hetxf74klffi19nbwk1` FOREIGN KEY (`CLIENT_USERNAME`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservations`
--

LOCK TABLES `Reservations` WRITE;
/*!40000 ALTER TABLE `Reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Restaurants`
--

DROP TABLE IF EXISTS `Restaurants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Restaurants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `description` text,
  `endTime` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `managerUsername` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c6yucds7b9ybb8p5waj239pv2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Restaurants`
--

LOCK TABLES `Restaurants` WRITE;
/*!40000 ALTER TABLE `Restaurants` DISABLE KEYS */;
INSERT INTO `Restaurants` VALUES (1,'Pittsburgh','US','At our gastropub, we don\'t distinguish between commoners and kings; we just want to feed the good people of Pittsburgh. In the restaurant, seasonal menus add a modern flair to classic comforts, complemented by a robust selection of local beers and craft spirits. It\'s all served in an industrial-inspired setting in downtown Pittsburgh. Come and join us for an uncommonly good time.','23:00','https://resizer.otstatic.com/v2/photos/xlarge/1/31676318.webp','ali','The Commoner','07:00','620 William Penn Place','American'),(2,'Pittsburgh','US','Welcome to Sullivan\'s Steakhouse in Pittsburgh. You can look forward to hand-cut steaks, fresh seafood, signature cocktails and lively music, all designed to let you untie, unwind and uncork. Located in bustling downtown Pittsburgh, on the first floor of the U.S Steel Tower, this one-of-a-kind steakhouse transports you to another place and time.','23:00','https://resizer.otstatic.com/v2/photos/xlarge/2/47090807.webp','amin','Sullivan\'s Steakhouse','11:00','Grant St. &amp; 6th Ave.','Steakhouse'),(3,'Pittsburgh','US','Quality, exceptional service, and an unparalleled atmosphere have made Eddie Merlot\'s one of America\'s great steak houses over the past 20 years. Eddie Merlot\'s is an homage to the man and his love of a great steak, a great glass of wine, and a good story.','21:00','https://resizer.otstatic.com/v2/photos/xlarge/1/25740940.webp','mohammad','Eddie Merlot\'s Prime Aged Beef & Seafood','11:00','444 Liberty Ave, Ste 100','Steak'),(4,'San Diego','US','The Winery Restaurant & Wine Bar has been focused on successfully pairing contemporary California regional cuisine, with a hip, vibrant, sophisticated setting, to create a cutting-edge dining experience. When Partners JC Clow, William Lewis and Chef Yvon Goetz set out to deliver a culinary experience straight from wine county, little did they know that they would also earn the title of Restaurateurs of the Year for their achievements and have their restaurants in Newport Beach and Tustin earn the title of Restaurant of the Year multiple times! The three partners strive to deliver the best dining experience In Orange County on a nightly basis. Chef Goetz, whose accolades include the AAA Five Diamond Award and multiple Chef of the Year honors, describes his menu at The Winery Restaurant as wine country-driven a perfect match for a wine program that features a list with 650 selections that change weekly and climate-controlled cellars, which can house up to 7,500 bottles!','21:00','https://resizer.otstatic.com/v2/photos/xlarge/3/42273419.webp','amin','The Winery Restaurant & Wine Bar','12:00','4301 La Jolla Village Drive Suite 2040','Seafood'),(5,'San Diego','US','Et Voilà! is a vibrant neighborhood bistro, located along the North Park corridor. Et Voilà! French Bistro showcases Chef Vincent Viale\'s talents, mixing French classics with a modern accent. Find local and seasonal products building upon French recipes from various regions, such as Steak Frites, Coquilles St. Jacques, and soufflés. Explore the diverse regions of France through a curated wine list, and taste a variety of distinct local ingredients with our crafted cocktails. Et Voila! French Bistro is the destination for those looking for a vibrant dining experience, or even for those looking for a simple glass of wine and informal bite at the bar.','23:00','https://resizer.otstatic.com/v2/photos/xlarge/3/53115407.webp','hasan','Et Voilà!','16:00','4301 La Jolla Village Drive Suite 2040','French'),(6,'San Diego','US','At North, we focus on what we do best: Italian from scratch. Enjoy any of our handmade pastas and pizzas, created with seasonal ingredients and inventive flavors. Or, try a different signature dish, prepared with our fresh flavors from the garden, farm, and sea. We\'ve hand crafted seasonal cocktails that pair great with our food, creating a real modern Italia experiences perfect for any occasion.','22:00','https://resizer.otstatic.com/v2/photos/xlarge/1/25879481.webp','alireza','North Italia','11:00','7055 Friars Road','Italian'),(7,'Tokyo','Japan','Super popular Japanese culture event September 28th, Wine Festival from October 1st to 6th, Halloween-only menu from October 7th, Book your spot NOW','05:00','https://resizer.otstatic.com/v2/photos/xlarge/5/26465698.webp','reza','KUJIRA','21:00','Shinjuku','Bar'),(8,'Edmonton','Canada','A mecca for quality steaks, chops and fresh seafood in Edmonton\'s ICE District, Braven embraces a “go big or go home” attitude. Located in the heart of Edmonton\'s ICE District, we invite our guests to abandon all hesitation, embrace adventure and enjoy life to its fullest.','22:00','https://resizer.otstatic.com/v2/photos/xlarge/2/47106621.webp','amin','Braven','06:00','10344 102 St NW','Steak'),(9,'Edmonton','Canada','We are a floral-inspired restaurant located in downtown Edmonton. We offer artisan coffee and a wide range of beautifully curated dishes made from locally sourced ingredients. Choose to dine within two unique dining areas: The Flower Tree and The Flower House.','21:00','https://resizer.otstatic.com/v2/photos/xlarge/2/47734196.webp','amin','BREW+BLOOM','09:00','10550 115 St NW','Contemporary Canadian'),(10,'Edmonton','Canada','Welcome to Earls 170th Street, your go-to destination for a memorable modern dining experience in our recently renovated space. Discover our enticing daily features, unwind during Happy Hour, and enjoy late-night Happy Hour offerings. Immerse yourself in the lively ambiance of our patio, where you can bask in the outdoors while savouring delicious food and drinks. Whether you\'re looking for a casual evening or a vibrant night out, we have you covered. Your table is waiting. We can\'t wait to see you!','23:00','https://resizer.otstatic.com/v2/photos/xlarge/3/52163623.webp','amin','Earls Kitchen + Bar','11:00','170th Street','American'),(11,'Frankfurt','Germany','Eine Champagner-Weinbar, die von Spanien, Italien und Frankreich lebt. Die Weintrilogie, die in den Gläsern perlt. Mitten in Frankfurt.','23:00','https://resizer.otstatic.com/v2/photos/xlarge/3/54305906.webp','amin','Casa De Rosé','11:00','Opernpl. 14, 60313 Frankfurt am Main','Mediterranean'),(12,'Frankfurt','Germany','The restaurant offers authentic, fresh, homemade and handmade national dishes of modern Mexican cuisine. The menu includes fish and meat, as well as vegetarian, gluten-free and vegan dishes and sweet delicacies. The offer is based on a sharing model.','23:00','https://resizer.otstatic.com/v2/photos/xlarge/3/49589055.webp','amin','Restaurant Club Social Mexicano','11:00','Große Eschenheimer Straße','Mexican'),(13,'Frankfurt','Germany','Freuen Sie sich auf eine kulinarische Reise durch die Welt der asiatischen Fine Dining Cuisine. Im Zukaya trifft man auf eine elegante und gemütliche Atmosphäre, die das perfekte Ambiente für ein romantisches Abendessen zu zweit, ein Treffen mit Freunden oder Geschäftspartnern oder eine festliche Feier mit der Familie schafft. Lassen Sie sich von bekannten asiatischen Köstlichkeiten sowie einzigartigen Sushi-Kreationen verführen und tauchen Sie ein in eine Oase des Genusses.','22:00','https://resizer.otstatic.com/v2/photos/xlarge/1/56419384.webp','amin','Zukaya','10:00','Steigenberger Frankfurter Hof, Bethmannstraße 33, 60311 Frankfurt am Main','Asian'),(14,'Frankfurt','Germany','','01:00','https://resizer.otstatic.com/v2/photos/xlarge/2/43476531.webp','amin','Charlot','12:00','Opernplatz 10, Frankfurt am Main, HE 60313','Italian'),(15,'Frankfurt','Germany','','22:00','https://resizer.otstatic.com/v2/photos/xlarge/1/26090243.webp','amin','Restaurant Opera','11:00','Opernplatz 1, Frankfurt am Main, HE 60313','European'),(16,'Hamburg','Germany','','22:00','https://resizer.otstatic.com/v2/photos/xlarge/2/48899571.webp','amin','Nomad','12:00','Rotherbaum','Japanese'),(17,'Hamburg','Germany','Tarantella provides an upscale dining experience that even the most discerning of connoisseurs would appreciate. Centrally located in St. Stephen\'s square, Tarantella\'s evolution from a nightclub in the 1970s to its current form is reflected in its immaculately decorated interior. With Tarantella\'s open kitchen design, patrons can watch their dishes being carefully made by the restaurant\'s renown chefs. The restaurant also has a wine cellar stocked with rare vintages and other treasures. For an even more intimate dining experience, a wine cellar that fits up to 20 people is available for rent. For food, drink and ambiance, no other restaurant comes close to Tarantella.','02:00','https://resizer.otstatic.com/v2/photos/xlarge/1/25084166.webp','amin','Tarantella','12:00','Neustadt','German'),(18,'Calgary','Canada','Major Tom is elevated yet approachable, excellent and at ease, confident and gracious. The food, the room, and the service capture the sophistication and glamour of mid-century New York penthouse life, and you feel instantly at home. We maintain the high standards our guests have come to expect, and we do this while being down-to-earth, lively, playful, quirky, fun, and full of personality.','02:00','https://resizer.otstatic.com/v2/photos/xlarge/1/50825560.webp','amin','MAJOR TOM','12:00','Downtown','Continental'),(19,'Calgary','Canada','An all day neighbourhood eatery that boasts bold new North American cuisine and vegetable anchored dishes. Ten Foot Henry bridges the gap between what you should be eating, and what you really want to eat.','23:00','https://resizer.otstatic.com/v2/photos/xlarge/1/24384323.webp','amin','Ten Foot Henry','11:00','12th Avenue','Canadian'),(20,'Calgary','Canada','Hy\'s Calgary is a modern rendition of the classic steakhouse tradition. Pride of place is held by the circular, glass enclosed grill room, producing perfect charcoal grilled Canadian Prime Grade steaks.','21:00','https://resizer.otstatic.com/v2/photos/xlarge/1/53269670.webp','amin','Hy\'s Steakhouse','11:00','8th Avenue','Steakhouse');
/*!40000 ALTER TABLE `Restaurants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reviews`
--

DROP TABLE IF EXISTS `Reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Reviews` (
  `ambianceRate` float DEFAULT NULL,
  `foodRate` float DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `overallRate` float DEFAULT NULL,
  `serviceRate` float DEFAULT NULL,
  `datetime` datetime(6) DEFAULT NULL,
  `comment` text,
  `restaurantName` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm1ehbgowtsaxo1s5jhaoe7ati` (`restaurantName`),
  KEY `FK7gkxp802mtqnxar0nmncn9wop` (`username`),
  CONSTRAINT `FK7gkxp802mtqnxar0nmncn9wop` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `FKm1ehbgowtsaxo1s5jhaoe7ati` FOREIGN KEY (`restaurantName`) REFERENCES `Restaurants` (`name`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reviews`
--

LOCK TABLES `Reviews` WRITE;
/*!40000 ALTER TABLE `Reviews` DISABLE KEYS */;
INSERT INTO `Reviews` VALUES (4,4,1,4,3,NULL,'Enjoyed a pleasant dinner at Sullivan\'s. The steak was cooked perfectly, and the service was attentive. The ambiance was nice and cozy. Overall, a good experience.','Sullivan\'s Steakhouse','MohammadMehdi_Jafari'),(5,5,2,5,4,NULL,'Eddie Merlot\'s exceeded expectations! The steak was incredible, and the service was top-notch. The ambiance was perfect for a special evening out.','Eddie Merlot\'s Prime Aged Beef & Seafood','Kasri_HajiHeidari'),(4,4,3,4,5,NULL,'Had a lovely time at The Winery. The food was delicious, and the wine selection was impressive. Excellent service added to the overall enjoyment.','The Winery Restaurant & Wine Bar','Marzieh_Hariri'),(3,3,4,3,4,NULL,'Et Voilà! had a charming atmosphere. The food was decent, but the service was attentive. Overall, a nice experience.','Et Voilà!','Melika_HeidariDastjerdi'),(4,4,5,4,5,NULL,'North Italia offers delicious Italian cuisine. The service was exceptional, and the ambiance was lively. Will definitely visit again!','North Italia','AmirMohammad_Khodaei'),(2,2,6,2,3,NULL,'Disappointing experience at KUJIRA. The food was mediocre, and the service was slow. Ambiance was underwhelming. Not recommended.','KUJIRA','Razieh_DorehGard'),(4,4,7,4,4,NULL,'Braven offers a great selection of craft beers. The food was tasty, and the service was efficient. Enjoyed the laid-back ambiance.','Braven','Shahab_Rakhsha'),(4,5,8,5,5,NULL,'BREW+BLOOM is a hidden gem! The coffee was exceptional, and the brunch menu was delightful. Friendly staff and cozy atmosphere.','BREW+BLOOM','MohammadHossien_Rezayati'),(4,4,9,4,4,NULL,'Zukaya offers authentic flavors. The service was attentive, and the ambiance was cozy. Enjoyed the dining experience.','Zukaya','Fatimeh_Sharifi'),(5,5,10,5,5,NULL,'Charlot is a fantastic French restaurant. The food was exquisite, and the service was impeccable. The ambiance was elegant and charming.','Charlot','AmirAli_Shahriary'),(4,4,11,4,3,NULL,'Had a pleasant evening at Restaurant Opera. The food was good, but the service was a bit slow. The ambiance was classy.','Restaurant Opera','Ahoora_Shiri'),(3,3,12,3,4,NULL,'Nomad offers an interesting menu. The service was decent, but the ambiance could be improved. Average dining experience.','Nomad','MohammadSaid_Sedighi'),(4,4,13,4,4,NULL,'Tarantella serves authentic Italian cuisine. The food was delicious, and the service was prompt. Enjoyed the cozy atmosphere.','Tarantella','Ali_Abedini'),(5,5,14,5,5,NULL,'MAJOR TOM is a must-visit! The food and service were exceptional, and the ambiance was out of this world. A memorable dining experience.','MAJOR TOM','Ali_Ataollahi'),(4,4,15,4,5,NULL,'Ten Foot Henry offers innovative dishes. The service was excellent, and the ambiance was lively. Will definitely return!','Ten Foot Henry','FatimaZahra_Broumandnia'),(3,3,16,3,4,NULL,'Tried The Commoner for brunch. The food was okay, but the service could have been better. The ambiance was lively and casual.','The Commoner','SeyedAmirhosien_Vahdat'),(4,4,17,4,5,NULL,'Visited Sullivan\'s Steakhouse for a special occasion. The steak was delicious, and the service was excellent. Enjoyed the classy ambiance.','Sullivan\'s Steakhouse','AmirAli_VahidiNogan'),(5,5,18,5,5,NULL,'Eddie Merlot\'s never disappoints! The food was superb, and the service was outstanding. The ambiance was perfect for a celebratory dinner.','Eddie Merlot\'s Prime Aged Beef & Seafood','Matin_Nabizadeh'),(4,4,19,4,4,NULL,'Had a great time at The Winery. The food was delicious, and the wine selection was impressive. The service was attentive.','The Winery Restaurant & Wine Bar','SeyedMohammad_Naghshbandi'),(4,3,20,3,3,NULL,'Et Voilà! offers authentic French cuisine. The food was decent, but the service was slow. The ambiance was cozy and rustic.','Et Voilà!','Ali_Hodaei'),(4,4,21,4,5,NULL,'North Italia is a great spot for Italian food. The pasta dishes were delicious, and the service was excellent. Lively atmosphere.','North Italia','SeyedHamid_MiramlrKhan'),(2,2,22,2,3,NULL,'Disappointed with KUJIRA. The food was mediocre, and the service was slow. The ambiance was lacking atmosphere.','KUJIRA','Parsa_Nassery'),(4,4,23,4,4,NULL,'Braven is a cool spot with great cocktails. The food was tasty, and the service was friendly. Enjoyed the laid-back ambiance.','Braven','Mohammad_Sadeghi'),(5,5,24,5,5,NULL,'BREW+BLOOM is my favorite coffee place! The coffee is always exceptional, and the brunch menu is fantastic. Cozy atmosphere.','BREW+BLOOM','Misagh_Mohaghegh'),(3,3,25,3,4,NULL,'Earls Kitchen + Bar has a lively vibe. The food was decent, but the service could be more attentive during busy hours. Average experience.','Earls Kitchen + Bar','Fatima_Mohammadi'),(5,4,26,4,4,NULL,'Casa De Rosé is perfect for a romantic dinner. The food was delicious, and the service was good. Lovely ambiance.','Casa De Rosé','Soghol_MohammadiToucheye'),(4,3,27,3,3,NULL,'Visited Restaurant Club Social Mexicano with friends. The food was average, and the service was a bit slow. Vibrant atmosphere.','Restaurant Club Social Mexicano','Ali_Momtahen'),(4,4,28,4,4,NULL,'Zukaya offers unique flavors. The service was attentive, and the ambiance was cozy. Enjoyed the dining experience.','Zukaya','Sana_Sarinavai'),(5,5,29,5,5,NULL,'Charlot is a gem! The food was outstanding, and the service was top-notch. Loved the charming ambiance.','Charlot','Fatima_Shahhosseini'),(4,4,30,4,5,NULL,'Had a wonderful dining experience at Restaurant Opera. The food was delicious, and the service was impeccable. Elegant ambiance.','Restaurant Opera','Arash_Shahin'),(3,3,31,3,4,NULL,'Nomad offers exotic dishes. The food was decent, but the service was slow at times. Interesting ambiance.','Nomad','Mohammad_Fateh'),(4,4,32,4,4,NULL,'Tarantella is a cozy Italian restaurant. The food was tasty, and the service was friendly. Nice ambiance for a casual meal.','Tarantella','AmirMehdi_Farzane'),(5,5,33,5,5,NULL,'MAJOR TOM is a must-visit! The food and service were exceptional, and the ambiance was out of this world. A memorable dining experience.','MAJOR TOM','MohammadHossien_Aqili'),(4,4,34,4,5,NULL,'Ten Foot Henry offers innovative dishes. The service was excellent, and the ambiance was lively. Will definitely return!','Ten Foot Henry','Ali_GhanbariGarji'),(4,3,35,3,3,NULL,'Sullivan\'s Steakhouse has a classic vibe. The food was decent, but the service could have been more attentive. Good for special occasions.','Sullivan\'s Steakhouse','Moein_Karami'),(5,5,36,5,5,NULL,'Eddie Merlot\'s is the best place for steak and seafood! The food and service exceeded my expectations. Elegant ambiance.','Eddie Merlot\'s Prime Aged Beef & Seafood','Amirhosien_Kahrobayian'),(4,4,37,4,4,NULL,'The Winery Restaurant & Wine Bar is perfect for wine lovers. The food was delicious, and the service was friendly. Relaxed ambiance.','The Winery Restaurant & Wine Bar','Ali_Lotfollahee'),(4,4,38,4,4,NULL,'Et Voilà! offers authentic French cuisine. The food was flavorful, and the service was attentive. Cozy atmosphere.','Et Voilà!','Mohammad_Sadeghi'),(5,5,39,5,5,NULL,'North Italia is a great spot for Italian food. The pasta dishes were delicious, and the service was excellent. Lively atmosphere.','North Italia','Misagh_Mohaghegh'),(2,2,40,2,3,NULL,'Disappointed with KUJIRA. The food was mediocre, and the service was slow. The ambiance was lacking atmosphere.','KUJIRA','Fatima_Mohammadi'),(4,4,41,4,4,NULL,'Braven is a cool spot with great cocktails. The food was tasty, and the service was friendly. Enjoyed the laid-back ambiance.','Braven','Soghol_MohammadiToucheye'),(5,5,42,5,5,NULL,'BREW+BLOOM is my favorite coffee place! The coffee is always exceptional, and the brunch menu is fantastic. Cozy atmosphere.','BREW+BLOOM','Ali_Momtahen'),(3,3,43,3,4,NULL,'Earls Kitchen + Bar has a lively vibe. The food was decent, but the service could be more attentive during busy hours. Average experience.','Earls Kitchen + Bar','Sana_Sarinavai'),(5,4,44,4,4,NULL,'Casa De Rosé is perfect for a romantic dinner. The food was delicious, and the service was good. Lovely ambiance.','Casa De Rosé','Fatima_Shahhosseini'),(4,3,45,3,3,NULL,'Visited Restaurant Club Social Mexicano with friends. The food was average, and the service was a bit slow. Vibrant atmosphere.','Restaurant Club Social Mexicano','Arash_Shahin'),(4,4,46,4,5,NULL,'The Commoner is a delightful spot! The food was excellent, and the service was impeccable. Cozy ambiance perfect for brunch.','The Commoner','SeyedAmirhosien_Vahdat'),(3,3,47,3,4,NULL,'Visited The Commoner for dinner. The food was decent, but the service could have been more attentive. Casual atmosphere.','The Commoner','AmirAli_VahidiNogan'),(4,5,48,5,5,NULL,'The Commoner exceeded my expectations! The food was amazing, and the service was top-notch. Lively atmosphere.','The Commoner','Matin_Nabizadeh'),(4,4,49,4,4,NULL,'Had a wonderful brunch experience at The Commoner. The food was delicious, and the service was friendly. Relaxed vibe.','The Commoner','SeyedMohammad_Naghshbandi'),(3,3,50,3,3,NULL,'The Commoner offers a decent brunch menu. The food was average, and the service was a bit slow. Cozy atmosphere.','The Commoner','Ali_Hodaei'),(4,4,51,4,5,NULL,'Sullivan\'s Steakhouse is a classic choice for steak. The food was delicious, and the service was impeccable. Elegant ambiance.','Sullivan\'s Steakhouse','AmirAli_VahidiNogan'),(4,3,52,3,3,NULL,'Sullivan\'s Steakhouse has a nice ambiance. The food was decent, but the service could have been better. Good for special occasions.','Sullivan\'s Steakhouse','Moein_Karami'),(4,4,53,4,4,NULL,'Enjoyed a lovely dinner at Sullivan\'s Steakhouse. The steak was flavorful, and the service was friendly. Classic steakhouse atmosphere.','Sullivan\'s Steakhouse','Ali_Momtahen'),(3,3,54,3,4,NULL,'Visited Sullivan\'s Steakhouse for a special occasion. The food was okay, but the service could have been more attentive. Classic setting.','Sullivan\'s Steakhouse','SeyedMohammad_Naghshbandi'),(5,5,55,5,5,NULL,'Sullivan\'s Steakhouse never disappoints! The steak was cooked perfectly, and the service was outstanding. Elegant and classy ambiance.','Sullivan\'s Steakhouse','Matin_Nabizadeh'),(5,5,56,5,5,NULL,'Eddie Merlot\'s is my go-to for steak! The food and service were exceptional, and the ambiance was perfect for a special dinner.','Eddie Merlot\'s Prime Aged Beef & Seafood','Amirhosien_Kahrobayian'),(4,4,57,4,4,NULL,'Had a wonderful dining experience at Eddie Merlot\'s. The steak was delicious, and the service was attentive. Upscale ambiance.','Eddie Merlot\'s Prime Aged Beef & Seafood','SeyedAmirhosien_Vahdat'),(4,4,58,4,5,NULL,'Eddie Merlot\'s never disappoints! The steak was cooked perfectly, and the service was excellent. Elegant and upscale atmosphere.','Eddie Merlot\'s Prime Aged Beef & Seafood','Ali_GhanbariGarji'),(5,5,59,5,5,NULL,'Celebrated a special occasion at Eddie Merlot\'s. The food, service, and ambiance were outstanding. Highly recommend for steak lovers!','Eddie Merlot\'s Prime Aged Beef & Seafood','Matin_Nabizadeh'),(5,5,60,5,5,NULL,'Eddie Merlot\'s is the epitome of fine dining! The steak was exceptional, and the service was impeccable. Upscale and elegant atmosphere.','Eddie Merlot\'s Prime Aged Beef & Seafood','MohammadHossien_Aqili');
/*!40000 ALTER TABLE `Reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tables`
--

DROP TABLE IF EXISTS `Tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tables` (
  `seatsNumber` int DEFAULT NULL,
  `tableNumber` int NOT NULL AUTO_INCREMENT,
  `managerUsername` varchar(255) DEFAULT NULL,
  `restaurantName` varchar(255) NOT NULL,
  PRIMARY KEY (`tableNumber`),
  KEY `FKrqeyte4ei48igi404ev6t65hh` (`restaurantName`),
  CONSTRAINT `FKrqeyte4ei48igi404ev6t65hh` FOREIGN KEY (`restaurantName`) REFERENCES `Restaurants` (`name`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tables`
--

LOCK TABLES `Tables` WRITE;
/*!40000 ALTER TABLE `Tables` DISABLE KEYS */;
INSERT INTO `Tables` VALUES (4,1,'ali','The Commoner'),(6,2,'ali','The Commoner'),(8,3,'ali','The Commoner'),(10,4,'ali','The Commoner'),(12,5,'ali','The Commoner'),(2,6,'amin','Sullivan\'s Steakhouse'),(4,7,'amin','Sullivan\'s Steakhouse'),(6,8,'amin','Sullivan\'s Steakhouse'),(8,9,'amin','Sullivan\'s Steakhouse'),(2,10,'mohammad','Eddie Merlot\'s Prime Aged Beef & Seafood'),(4,11,'mohammad','Eddie Merlot\'s Prime Aged Beef & Seafood'),(6,12,'mohammad','Eddie Merlot\'s Prime Aged Beef & Seafood'),(8,13,'mohammad','Eddie Merlot\'s Prime Aged Beef & Seafood'),(4,14,'amin','The Winery Restaurant & Wine Bar'),(6,15,'amin','The Winery Restaurant & Wine Bar'),(8,16,'amin','The Winery Restaurant & Wine Bar'),(10,17,'amin','The Winery Restaurant & Wine Bar'),(12,18,'amin','The Winery Restaurant & Wine Bar'),(2,19,'hasan','Et Voilà!'),(4,20,'hasan','Et Voilà!'),(6,21,'hasan','Et Voilà!'),(8,22,'hasan','Et Voilà!'),(8,23,'alireza','North Italia'),(10,24,'alireza','North Italia'),(12,25,'alireza','North Italia'),(14,26,'alireza','North Italia'),(16,27,'alireza','North Italia'),(4,28,'reza','KUJIRA'),(6,29,'reza','KUJIRA'),(8,30,'reza','KUJIRA'),(10,31,'reza','KUJIRA'),(12,32,'reza','KUJIRA');
/*!40000 ALTER TABLE `Tables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_user`
--

DROP TABLE IF EXISTS `_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('USER','ADMIN','MANAGER') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_user`
--

LOCK TABLES `_user` WRITE;
/*!40000 ALTER TABLE `_user` DISABLE KEYS */;
INSERT INTO `_user` VALUES (1,'admin@mail.com','Admin','Admin','$2a$10$unSTc0j4gNCli75GMdkexOABjIdoyvnRbde60FSwUKmkjURawfidW','ADMIN'),(2,'manager@mail.com','Admin','Admin','$2a$10$5SaCpTXXtKZW7w0Pm6W5NOcqVxf7145CAJhP4suWzqLFKr4nXSZ3W','MANAGER');
/*!40000 ALTER TABLE `_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `expired` bit(1) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `revoked` bit(1) NOT NULL,
  `user_id` int DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` enum('BEARER') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pddrhgwxnms2aceeku9s2ewy5` (`token`),
  KEY `FKiblu4cjwvyntq3ugo31klp1c6` (`user_id`),
  CONSTRAINT `FKiblu4cjwvyntq3ugo31klp1c6` FOREIGN KEY (`user_id`) REFERENCES `_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (_binary '\0',1,_binary '\0',1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsLmNvbSIsImlhdCI6MTcxODYwNjYzMSwiZXhwIjoxNzE4NjkzMDMxfQ.2WemNQFEvRxuVuU8y34A5zO_KwJMGeknC9ShJtp88_o','BEARER'),(_binary '\0',2,_binary '\0',2,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyQG1haWwuY29tIiwiaWF0IjoxNzE4NjA2NjMxLCJleHAiOjE3MTg2OTMwMzF9.QzwoacfkaOaVqDFTjbRBSn8SfeplJTxKNNE3vu7XOrc','BEARER');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `role` varchar(31) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('client','Calgary','Canada','abolfazl.eslami@ut.ac.ir','$2a$10$6yXZFIZ5Q16VdkKGYTbr9.LyR5yOjZ/nHU8p6/9zYXrwTAhUCvQsi','Abolfazl_EslamiAliAbadi'),('client','Frankfurt','Germany','ahoora.shiri@ut.ac.ir','$2a$10$dmxfpVEsCWUtqPPgho3nFeXdg6C2c9l/kiJ329NbS3RbWlAgc18lq','Ahoora_Shiri'),('client','Pittsburgh','US','ali@gmail.com','$2a$10$K.y.MKurpf3kfcw5SNF2YuBSlykHJuk0d5XDlQOq.gMSj9PBbY6Eq','ali'),('client','Pittsburgh','US','aliabedini2001@ut.ac.ir','$2a$10$/EYXyuVSG.GRYy61rDkgSuQ5ZeCoyb07Fz7WBOEMHTp4tW2KKWJW6','Ali_Abedini'),('client','Tokyo','Japan','ali.ataollahi81@ut.ac.ir','$2a$10$IkEK1d9cM0nYVXFjnFAxxO3CCYOFnTycTnR37tVKA1QOMBaaxdQb2','Ali_Ataollahi'),('client','Tokyo','Japan','ali.ghadyani@ut.ac.ir','$2a$10$pBp.NDsVOdCTo4Ka2IaOGOSTiaV6bgEIVozvU6G0w7t0GOjiSAdLK','Ali_Ghadyani'),('client','Calgary','Canada','ali.ghanbari@ut.ac.ir','$2a$10$0xz9Yhe4DFesxtEsaqbGeeurGJ4jfyhrqG8I9ZjC72sQVmAgAzkiy','Ali_GhanbariGarji'),('client','Tokyo','Japan','ali.hodaei@ut.ac.ir','$2a$10$TakoE/XjKs7ifXNKsuAkTeUn/RPV.MT4piWjgqGWVbHWQrYUSQi4a','Ali_Hodaei'),('client','Calgary','Canada','alilotfollahi2000@ut.ac.ir','$2a$10$E6bWbhXkUe1BOtLDwulNPObJRnINFsRbru3q7VHXTplTMcnxje/j.','Ali_Lotfollahee'),('client','San Diego','US','ali.momtahen@ut.ac.ir','$2a$10$WPT5Hs7yhQhcybgmQjX2VuIJJcvx3KAKNlHDq4AQ70I8inW7lq6g.','Ali_Momtahen'),('client','San Diego','US','alireza@gmail.com','$2a$10$jCQlk0Z35d/DGgqSh2SbyuJJjLvUJ5qWhFr4WcOPYmpDNfkE9K3aC','alireza'),('client','Pittsburgh','US','amin@gmail.com','$2a$10$ArCgvEkaQHlDyU/qPYhUy.LqSC7rYioUrwtv8fqUIT0fuaicx1Wju','amin'),('client','Hamburg','Germany','amirali.parsa@ut.ac.ir','$2a$10$PL2qJKMtDon2snt/NdnFA.ZcrpayhVIO8UNosJ9HfpzOrpodh7LEG','AmirAli_Parsa'),('client','Tokyo','Japan','amirali.shahriary@ut.ac.ir','$2a$10$8HetnFNPDrbh8wFGtcGxm.vLfRmJ7Hjj6LsCl3e8RoiITzo7l8ijS','AmirAli_Shahriary'),('client','Calgary','Canada','amir.ali.vahidi@ut.ac.ir','$2a$10$bApNRpMBoPEw7qijMttEH.BQtlmHqB9sKfwWyWa1MYLsuKf4JEKY2','AmirAli_VahidiNogan'),('client','Frankfurt','Germany','kahrobaeian.amir@ut.ac.ir','$2a$10$KSZfasZcWwQOEpdOQoKr/eH0y8EF7A2vKRj7YiDYFk6SUnsMXW2Hq','Amirhosien_Kahrobayian'),('client','San Diego','US','amirmfarzane@ut.ac.ir','$2a$10$5xBHmygSBqHKLd5k2EXnU.PyzBQXYtBKiO52GZXjjNu9Ktdfwntea','AmirMehdi_Farzane'),('client','Tokyo','Japan','amir.m.khodaei@ut.ac.ir','$2a$10$Jd66pl7xuP142jwm6rpGBO.N/WlOSdc9Cd6Ybjjq8N9CeBSbhXw7a','AmirMohammad_Khodaei'),('client','Calgary','Canada','arash.shahin@ut.ac.ir','$2a$10$SHixCbcY35L3nvpHBImG7.uYA4ufSNhqPX6L9l617g9qsAKIJ.Fdu','Arash_Shahin'),('client','Calgary','Canada','armita.bahrudi@ut.ac.ir','$2a$10$sjh2NHLObBUVLxh6NJt0Cukdx9iParOKk1bkAYGDAJr1264p8D75.','Armita_Bahrodi'),('client','Edmonton','Canada','a.abolghasemi@ut.ac.ir','$2a$10$nVdiuexBU9zH.efZ/6nRG.4I.ACOKLPCHJz8sfeJZ3GuDuDnEoPiK','Arshia_Abolghasemi'),('client','Edmonton','Canada','ashkanamany@ut.ac.ir','$2a$10$NrBncGEnt61cDQh6HIBu7O1vrdhaaOriEKtxAaaYRjRP67x1Kb8XK','Ashkan_Ammani'),('client','Tokyo','Japan','behyna.ansarirad@ut.ac.ir','$2a$10$vdnCZ81s1mlJeRBMuH4qq.WUZlV1ECNJ61zZe1rDiSCnPpfs5Hz5e','Behina_AnsariRad'),('client','Frankfurt','Germany','farhadghanbari@ut.ac.ir','$2a$10$xNuuGLUkZUj9Ykr22MqKfuJcAmyHChEcEr07mnRlLMlamngcXac4G','Farhad_GhanbariEstadKolayeh'),('client','Tokyo','Japan','fateme.arbabi@ut.ac.ir','$2a$10$OtnMY1G0XW4YmNXrVM9tiuO4FvB2ohR3HCDym7W.9tdw.IPSdQbXu','Fateme_ArbabiBidgalie'),('client','Hamburg','Germany','f.mohammadi.08@ut.ac.ir','$2a$10$43rQP8pfNlpi5ywICJW4bO7eneqlYaAr1Ef5jmO51Vg2p.DfdpeGq','Fatima_Mohammadi'),('client','Frankfurt','Germany','f.sh.shahhosseini@ut.ac.ir','$2a$10$F/HYGan3Pb1GacGl1PG8qO/aJahhDPleE/W6O7.uDTbeG.5VfrVCO','Fatima_Shahhosseini'),('client','Tokyo','Japan','fz.broumandnia@ut.ac.ir','$2a$10$yHUrZ8m9KP2k6hoKc2eBFundEJXN3uxr.it8aDpz8.NkTm9kSj2H6','FatimaZahra_Broumandnia'),('client','Edmonton','Canada','fbaharvand@ut.ac.ir','$2a$10$a91Zwyhy6sRej5.l1W1Ohu57EDR.G58/DY.VZQUcF64VzdFGW4OtO','Fatime_Baharvand'),('client','San Diego','US','fatemeh.sharifi@ut.ac.ir','$2a$10$r6wkeofWXRJboduzgPHw.OT41niBc6mdZAyD8h4PyfUk.b/5//81O','Fatimeh_Sharifi'),('client','San Diego','US','hasan@gmail.com','$2a$10$Jra6cHIRpPB02RxjY8E5ZeI38GI8u4Z2dDm4A7SmwfyHmsY5LKoJC','hasan'),('client','Calgary','Canada','hajiheidari.k@ut.ac.ir','$2a$10$Y6E33fakCWdZWnO4Rxu/z.OmkM8aKlaoEJ4LXWeGVC/UqyJgX826.','Kasri_HajiHeidari'),('client','Tokyo','Japan','maryam.jafarabadi@ut.ac.ir','$2a$10$og8fLq.J0xoMWnkDsOEdeu1VfVcqDgL21R5M1cbWrKvkzo9yuTHc.','Maryam_JafarabadiEshtiani'),('client','San Diego','US','marzieh.hariri@ut.ac.ir','$2a$10$p6/ATXTeFt4a59ehaAjl.e/V/QEQDUngMyeryEzM0a58.w8TIDCYu','Marzieh_Hariri'),('client','Calgary','Canada','matin.nabizadeh@ut.ac.ir','$2a$10$u7IhXn8FJIS5n2u96cQBieHGqm.j/ksbeG7XVNuUrWrM01LaQGkra','Matin_Nabizadeh'),('client','Calgary','Canada','mpourhossein@ut.ac.ir','$2a$10$eToVKZx33YI8cbgsocKfOeV4qT97JlKnNBQFvP0QQmrZYFJ6V8C4a','Mehdi_Pourhossein'),('client','Frankfurt','Germany','melikaheidari@ut.ac.ir','$2a$10$IjtcEIAhg45tGOlslcs52.70YNPsjSZ4t1NrtwugRln1t62TwYUw6','Melika_HeidariDastjerdi'),('client','Tokyo','Japan','misagh.mohaghegh@ut.ac.ir','$2a$10$Sb4rH.1G2UT.szbicgJ/f.7I.eCLoqaPUg7eWYddlIryM5tMp9DP.','Misagh_Mohaghegh'),('client','Tokyo','Japan','moein.karami@ut.ac.ir','$2a$10$9Hj7Bnrnf89pXPwU/sWJHOnCjeIhtrJiA2nfpcnj/EcWlGlFBB1Za','Moein_Karami'),('client','Pittsburgh','US','mohammad@gmail.com','$2a$10$oNOtg463W0r9CS1xTynHMOK5X.jYYkJ.ybnqW/GH9qlj09unxeWFO','mohammad'),('client','Edmonton','Canada','mohammad.fateh256@ut.ac.ir','$2a$10$ibn3trqgWUgGFq3lOUah4O15sLaRdo3OvbKPvUjFP/f/Ya8XnWMua','Mohammad_Fateh'),('client','Pittsburgh','US','msadegi@ut.ac.ir','$2a$10$0KF3w2TWxW7ZSJ0D4Zf9IekKHrUaC21TTsOVBDxf7DTV8ArN1zHRS','Mohammad_Sadeghi'),('client','Hamburg','Germany','mhaghili02@ut.ac.ir','$2a$10$QbR9ydDOOkUif4fE6UIAtOw2HR80ZBgXpEfjUQHO5JqjQjwKsQVp6','MohammadHossien_Aqili'),('client','Pittsburgh','US','rezayati@ut.ac.ir','$2a$10$r/pMi2ixYKhqrS/wz.pPcORpvEixr/9h1N7GQeqj7ZkDb3d4Ws56u','MohammadHossien_Rezayati'),('client','Hamburg','Germany','javad.afsari@ut.ac.ir','$2a$10$rhWm0IMI5rV50CTqy0dgyOdN1J13rZtN9euzxTLcAICit7T9uZzfW','MohammadJavad_Afsari'),('client','Frankfurt','Germany','javad.besharati@ut.ac.ir','$2a$10$ccBdJzmW1.1uhfh9QaBdrug73ci9jFvo9isbMbRFRAF0SaAwvGq.6','MohammadJavad_Besharati'),('client','Frankfurt','Germany','jafari.mahdi.moh@ut.ac.ir','$2a$10$Octw5bkIYbUFEgQOI2M3t.KlZirFk3UaqMuYVppaWmmtbA/BKvkJK','MohammadMehdi_Jafari'),('client','Pittsburgh','US','pouriyatajmehrabi@ut.ac.ir','$2a$10$YpvlHlrV2pdlan.Lx4X5iuzNb0GtfxyhIIqrbTvja.nbexy3M4xqO','MohammadPouriya_TajmehrabiNemini'),('client','San Diego','US','m.r.bakhshayesh@ut.ac.ir','$2a$10$iewPwWuv9znrCUd4Ha.I4uFlZ2UrMPYJqmaFQ1F5PO.tyVNJCsbme','MohammadReza_Bakhshayesh'),('client','San Diego','US','sadegh.aboofazeli@ut.ac.ir','$2a$10$5lm3uYfyDTghhsBw8uI.HOZdHXBnJNQVH4/c/oDUEAfFOJJdHTPHi','MohammadSadegh_Aboofazeli'),('client','Hamburg','Germany','ms.basseri@ut.ac.ir','$2a$10$zrbiC0IpMoSrDudzwMU74exZgFM52oVxXGVjRtDNO.1VkT1UAgDHu','MohammadSaid_Basseri'),('client','Calgary','Canada','mo.saeid.sedighi@ut.ac.ir','$2a$10$U5IeKs8cn/YYEqnNkC0fLuTHQKHTrXXgHQ1.LovnX7SDpXklXg.VG','MohammadSaid_Sedighi'),('client','Frankfurt','Germany','ebrahimi.mostafa@ut.ac.ir','$2a$10$r9JOok3pThbNmaNMxzi41e5xtTW34UIYynlgyN4ACmvDQWzCJKsjG','Mostafa_Ebrahimi'),('client','Frankfurt','Germany','parsa.nassery@ut.ac.ir','$2a$10$X7zSlk6KAxOc5eKi80Mg.urzz33dHAp.j9BTnT3rYAEFeLA2kXpaG','Parsa_Nassery'),('client','Edmonton','Canada','razieh.dorehgard@ut.ac.ir','$2a$10$NEJjagNAMk6W02LN/ODfwuHH258BVXG.1CGk6cmL9QG.4iZzw1uvy','Razieh_DorehGard'),('client','Tokyo','Japan','reza@gmail.com','$2a$10$0/x48fxai4OAcPBcmJIaYe3xJS6FVE0CndUqDDR46QNz1H2yRWxJK','reza'),('client','Tokyo','Japan','sana.sarinavaii@ut.ac.ir','$2a$10$pHb2tjDCgZ9vIDr752mL6.LJQOO1EmUgUoaGIdGHg3StZ7gBooQW2','Sana_Sarinavai'),('client','Pittsburgh','US','ali.emamzadeh@ut.ac.ir','$2a$10$FE6pnFBwV0a8CeCv0rMHK.rMYaRqQxoWvfFNPZncDhRo5o0gxqppm','SeyedAli_ImamZadeh'),('client','Frankfurt','Germany','810199577@email.invalid','$2a$10$SFw4lQUyvFI3.oQBaB.xE.lxVI7ytkR.26H2.75kh3DZc9vL/EXTG','SeyedAmirhosien_Vahdat'),('client','Tokyo','Japan','hamed.amirkhan@ut.ac.ir','$2a$10$FqOzJjnKLSAG5lLW9hqVTuGinQIWE5vXFD7mL8aDP3KzkYREI2ZUa','SeyedHamid_MiramlrKhan'),('client','Pittsburgh','US','sanan.naghshbandi@ut.ac.ir','$2a$10$YJD.pAdkvTXy7fSBt.zuMOr/Xxw3ZsT9YRvliDK1oVXvRCre3OIg6','SeyedMohammad_Naghshbandi'),('client','Hamburg','Germany','shahabrakhsha@ut.ac.ir','$2a$10$NCFKZ/CSyF2tVNppg3goJuzzp7yuOved51lFKEzEAW8WPNHEHTZyq','Shahab_Rakhsha'),('client','Pittsburgh','US','shayan.kashefi81@ut.ac.ir','$2a$10$yr6Hf9ZUDVf.bZ8wsErBpO1RyccwXNAi.QEMfcLOOhHC4dyHPJyRG','Shayan_KashefiShayeste'),('client','Edmonton','Canada','sogol.mohammadi@ut.ac.ir','$2a$10$Am0UNX7oNMYG91pp39aio.CIzW6GxqFrDtwuqEU2Ln5WyQevgYzz.','Soghol_MohammadiToucheye');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertoken`
--

DROP TABLE IF EXISTS `usertoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertoken` (
  `expired` bit(1) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `token_type` enum('BEARER') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6wtc4lw980wte1jl9aru9ur1x` (`token`),
  KEY `FKokgq1kgjjhytwhab2cu9w883b` (`username`),
  CONSTRAINT `FKokgq1kgjjhytwhab2cu9w883b` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertoken`
--

LOCK TABLES `usertoken` WRITE;
/*!40000 ALTER TABLE `usertoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `usertoken` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-17  6:48:03
