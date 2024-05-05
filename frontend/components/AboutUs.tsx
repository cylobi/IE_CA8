import React from "react";
import AboutUsBanner from "Frontend/public/images/AboutUsBanner.png";
function AboutUs() {
  return (
    <div className="row container mx-auto my-3" id="aboutus_container">
      <img alt="img" className="col float-left" src={AboutUsBanner} />
      <div className="col float-right">
        <article>
          <h4 className="text-red">About Mizdooni</h4>
          <p className="text-secondary">
            Are you tired of waiting in long lines at restaurants or struggling
            to find a table at your favorite eatery?
            <br />
            Look no further than Mizdooni – the ultimate solution for all your
            dining reservation needs.
            <br />
            Mizdooni is a user-friendly website where you can reserve a table at
            any restaurant, from anywhere, at a specific time. Whether you're
            craving sushi, Italian, or a quick bite to eat, Mizdooni has you
            covered.
            <br />
            With a simple search feature, you can easily find a restaurant based
            on cuisine or location.
            <br /> <br />
            <span className="text-danger">The best part?</span> There are no
            fees for making a reservation through Mizdooni. Say goodbye to the
            hassle of calling multiple restaurants or showing up only to find
            there's a long wait. With Mizdooni, you can relax knowing that your
            table is secured and waiting for you.
            <br />
            Don't let dining out be a stressful experience. Visit Mizdooni today
            and start enjoying your favorite meals without the headache of
            making reservations. Reserve your table with ease and dine with
            peace of mind.
          </p>
        </article>
      </div>
    </div>
  );
}

export default AboutUs;
