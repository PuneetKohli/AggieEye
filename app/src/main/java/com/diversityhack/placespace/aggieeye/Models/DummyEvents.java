package com.diversityhack.placespace.aggieeye.Models;

/**
 * Created by puneet on 03/02/18.
 */

public class DummyEvents {

  public static EventDetails[] getDummyEvents() {
    EventDetails[] events = new EventDetails[15];
    for (int i = 0; i < events.length; i++) {
      events[i] = new EventDetails();
    }
    events[0].setCategory(EventCategory.INFORMATION);
    events[0].setName("HACK-A-THON’18");
    events[0].setAddress("GEREN Auditorium, ARCB");
    events[0].setLatitude(30.618803);
    events[0].setLongitude(-96.337646);
    events[0].setTime("10 am - 6 pm");
    events[0].setLoves(153);
    events[0].setDoesLove(true);
    events[0].setAR(false);
    events[0].setHost("");
    events[0].setAbout(
        "Show off your creativity to improve Space & Place on the TAMU campus. The 24 hour Diversity Hackathon is sponsored by an interdisciplinary partnership between the College of Architecture Diversity Council; College of Engineering; College of Architecture ITS; University Libraries; Center on Disability & Development; Disability Services; GLBT Resource Center; and League of United Latin American Citizens, TAMU Chapter and is open to all TAMU faculty, staff and students. The event is a 24 - hour competition during which participants will creatively respond to Challenges presented by various TAMU departments(below), which pertain to the topic of Diversity on the Texas A & M campus.Participants will compete for $5, 000 in prizes for best projects addressing issues and inclusivity of space and place between students, faculty and staff face on campus and in everyday life.The event challenges participants for creative solutions to imagine a future campus without physical, technological, digital, cultural, psychological, etc.We want to peek into the future throughout the imagination and innovation of our participants. #TAMUDiversityHackathon ");
    events[0].setOrganization("TAMU College of Architecture Diversity Council");
    events[1].setCategory(EventCategory.INFORMATION);
    events[1].setName("Spring 2018 Scholarship Advising");
    events[1].setAddress("Rudder 501");
    events[1].setLatitude(30.617139);
    events[1].setLongitude(-96.343772);
    events[1].setTime("9 am - 10 am");
    events[1].setLoves(23);
    events[1].setDoesLove(false);
    events[1].setAR(false);
    events[1].setHost("Pablo Mora");
    events[1].setAbout(
        "Spring 2018 Scholarship Advising Session – Come and learn about the various scholarship opportunities available for TAMU students in Spring 2018.");
    events[1].setOrganization("Scholarships & Financial Aid");
    events[2].setCategory(EventCategory.ENTERTAINMENT);
    events[2].setName("Super Bowl Watch Party");
    events[2].setAddress("The Gardens Community Center 1");
    events[2].setLatitude(30.626842);
    events[2].setLongitude(-96.342590);
    events[2].setTime("5.30 pm - 10 pm");
    events[2].setLoves(40);
    events[2].setDoesLove(false);
    events[2].setAR(false);
    events[2].setHost("Shayla Houston");
    events[2].setAbout(
        "Come out and meet some new people, and enjoy a Sunday filledwith Fun, Friends, and Football !!!!We hope you all will come and celebrate with us. #SUPERBOWL FOOD AND DRINKS WILL BE PROVIDED !!!”");
    events[2].setOrganization("The Gardens Apartments");
    events[3].setCategory(EventCategory.SPORTS);
    events[3].setName("2017 Men's Basketball");
    events[3].setAddress("Reed Arena");
    events[3].setLatitude(30.605873);
    events[3].setLongitude(-96.346200);
    events[3].setTime("7.15 pm - 10 pm");
    events[3].setLoves(67);
    events[3].setDoesLove(false);
    events[3].setAR(false);
    events[3].setHost("");
    events[3].setAbout("TAMU vs. Kentucky");
    events[3].setOrganization("Texas A&M University");
    events[4].setCategory(EventCategory.STUDY);
    events[4].setName("Group Study Night");
    events[4].setAddress("Evans Library");
    events[4].setLatitude(30.616785);
    events[4].setLongitude(-90.339231);
    events[4].setTime("6 pm - 11 pm");
    events[4].setLoves(34);
    events[4].setDoesLove(false);
    events[4].setAR(false);
    events[4].setHost("TAMU Libraries");
    events[4].setAbout("Group study with free snacks and beverages !!");
    events[4].setOrganization("Texas A&M University");
    events[5].setCategory(EventCategory.STUDY);
    events[5].setName("Group Study for ISEN 625– Simulation Methods and Applications");
    events[5].setAddress("Evans 425");
    events[5].setLatitude(30.616285);
    events[5].setLongitude(-96.339931);
    events[5].setTime("1 pm - 5 pm");
    events[5].setLoves(3);
    events[5].setDoesLove(false);
    events[5].setAR(false);
    events[5].setHost("Mayank Jaggi");
    events[5].setAbout(
        "Setting up a group study for ISEN 625 – Simulation Methods and Applications at Evans 425. Please come by if you are interested in studying together.");
    events[5].setOrganization("Texas A&M University");
    events[6].setCategory(EventCategory.FOOD);
    events[6].setName("First Potluck of the Spring!");
    events[6].setAddress("iHouse, Milam St");
    events[6].setLatitude(30.626178);
    events[6].setLongitude(-96.346783);
    events[6].setTime("6 pm - 8 pm");
    events[6].setLoves(29);
    events[6].setDoesLove(true);
    events[6].setAR(false);
    events[6].setHost("");
    events[6].setAbout(
        "It's a new semester and it is time to see all of our friends again! We will all get together to eat, play games and catch up. Please bring one dish of food to share with everyone.");
    events[6].setOrganization("iHouse Aggieland");
    events[7].setCategory(EventCategory.INFORMATION);
    events[7].setName("Join and Support Carpool");
    events[7].setAddress("MSC 2500");
    events[7].setLatitude(30.612282);
    events[7].setLongitude(-96.341376);
    events[7].setTime("5.30 pm - 8 pm");
    events[7].setLoves(17);
    events[7].setDoesLove(false);
    events[7].setAR(false);
    events[7].setHost("");
    events[7]
        .setAbout(
            "CARPOOL is an organization that is dedicated to helping prevent drunk driving and providing a service that works to keep our community safe.Our members are arguably the best part about joining CARPOOL, as we are a tight - knit group of people with the same goal in mind:to prevent drunk driving.If you apply for CARPOOL, you will find a group of amazing people who all wish to help keep their community and fellow Aggies safe.You may even find a few life - long friends along the way. ");
    events[7].setOrganization("CARPOOL");
    events[8].setCategory(EventCategory.COMPETITION);
    events[8].setName("Movie Screening – Fantastic Mr. Fox");
    events[8].setAddress("Rudder 601");
    events[8].setLatitude(30.612832);
    events[8].setLongitude(-96.340305);
    events[8].setTime("7 pm - 10 pm");
    events[8].setLoves(28);
    events[8].setDoesLove(true);
    events[8].setAR(false);
    events[8].setHost("");
    events[8].setAbout(
        "MSC Aggie Cinema is a student organization within the Memorial Student Center.The committee aims to engage Texas A & M students by producing year - round entertaining, cultural, and educational programs that bring the best of cinema through our Blockbuster, Arthouse, Classic Series, Advanced Screenings, and other special events.MSC Aggie Cinema is composed of four subcommittees allowing it’s members to serve the organization, campus, and the community through hands - on event management experience. Come join us this week for the screening of Fantastic Mr.Fox.Make sure you invite your friends and get there early as the seats are limited #MovieNight ");
    events[8].setOrganization("Aggie Cinema");
    events[9].setCategory(EventCategory.FOOD);
    events[9].setName("Time4Chai");
    events[9].setAddress("Aggie BSM");
    events[9].setLatitude(30.618862);
    events[9].setLongitude(-96.346466);
    events[9].setTime("8 pm - 11 pm");
    events[9].setLoves(45);
    events[9].setDoesLove(true);
    events[9].setAR(false);
    events[9].setHost("Steven White");
    events[9].setAbout(
        "#DesiAggies is back with #Time4Chai this semester.Join in for Chai, Games and More !");
    events[9].setOrganization("Desi Aggies");
    events[10].setCategory(EventCategory.INFORMATION);
    events[10].setName("TAMU Consulting Group Informational");
    events[10].setAddress("Wehner 107");
    events[10].setLatitude(30.610859);
    events[10].setLongitude(-96.350611);
    events[10].setTime("7 pm - 9 pm");
    events[10].setLoves(16);
    events[10].setDoesLove(true);
    events[10].setAR(false);
    events[10].setHost("Danial Kordi");
    events[10].setAbout(
        "We aim to increase the knowledge of students by providing consulting work in different fashions - marketing research, financial analysis, and strategy development. We also hope to present our members with relevant consulting cases which the Big Four Accounting and Big Three Consulting firms have used to recruit some of their top talents. We are looking for talented sophomores, juniors, and seniors interested in consulting with a strong background in business. This would be a valuable opportunity to learn about business operations, and how to create innovative, effective solutions for clients, all while working as a team, which would prove very useful in your future career opportunities.");
    events[10].setOrganization("Texas A&M Consulting Group (TACG)");
    events[11].setCategory(EventCategory.FOOD);
    events[11].setName("ISMA Matching Social");
    events[11].setAddress("MSC Bethancourt Ballroom");
    events[11].setLatitude(30.612382);
    events[11].setLongitude(-96.341976);
    events[11].setTime("1 pm - 3 pm");
    events[11].setLoves(51);
    events[11].setDoesLove(true);
    events[11].setAR(true);
    events[11].setHost("Daniel Smith");
    events[11].setAbout(
        "Come to our Matching Social in the MSC! We will serve food, so come on down to meet your mentor for the first time and hang out. If you cannot make it, don't worry! We will also send out all the matching info so that you can either contact your mentor or they can contact you!");
    events[11].setOrganization("International Student Mentor Association");
    events[12].setCategory(EventCategory.STUDY);
    events[12].setName("Group Study for ISTM 637 ñ Data Warehousing");
    events[12].setAddress("WCL 215");
    events[12].setLatitude(30.611917);
    events[12].setLongitude(-96.349333);
    events[12].setTime("12 pm - 4 pm");
    events[12].setLoves(11);
    events[12].setDoesLove(false);
    events[12].setAR(true);
    events[12].setHost("Tushar Gupta");
    events[12].setAbout(
        "Howdy! I am looking for people willing to join in for a group study for the upcoming mid-term examination for ISTM 637 Data Warehousing. Anyone and everyone is welcome at WCL 215 from 12 pm through 4 pm");
    events[12].setOrganization("");
    events[13].setCategory(EventCategory.STUDY);
    events[13].setName("Group Study CSCE 629 ñ Analysis of Algorithms");
    events[13].setAddress("Evans Annex 315");
    events[13].setLatitude(30.616785);
    events[13].setLongitude(-96.339231);
    events[13].setTime("3 pm - 7 pm");
    events[13].setLoves(19);
    events[13].setDoesLove(true);
    events[13].setAR(false);
    events[13].setHost("Bhavesh Munot");
    events[13].setAbout(
        "Hosting a group study for CSCE 629 ñ Analysis of Algorithms at Evans Annex 315 this afternoon. Everyoneís welcome!");
    events[13].setOrganization("");
    events[14].setCategory(EventCategory.SPORTS);
    events[14].setName("Texas A&M Quidditch Intramurals");
    events[14].setAddress("Penberthy Recreational Complex");
    events[14].setLatitude(30.602053);
    events[14].setLongitude(-96.346732);
    events[14].setTime("8.15 pm ñ 9.15 pm");
    events[14].setLoves(44);
    events[14].setDoesLove(false);
    events[14].setAR(true);
    events[14].setHost("Chris Choquette");
    events[14].setAbout("");
    events[14].setOrganization(
        "Itís Time to #Quidditch! Today is the first night of Texas A&M Quidditch Intramurals! Intramurals will be held at the Penberthy Recreational Complex by Lot 58. We will be using the fields next to the softball fields, look for the hoops if you are having trouble locating the correct area. Tonight's matches will be between Grey versus Black and White versus Maroon.");

    return events;
  }
}
