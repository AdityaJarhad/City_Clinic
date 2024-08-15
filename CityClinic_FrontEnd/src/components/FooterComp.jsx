import { Footer } from "flowbite-react";
import { FaDribbble, FaFacebook, FaGithub, FaInstagram, FaTwitter } from "react-icons/fa6";

const FooterComp = () => {
  return (
    <Footer bgDark>
      <div className="w-full px-4 lg:px-24 bg-gray-700 text-white">
        <div className="grid w-full grid-cols-2 gap-8 px-6 py-8 md:grid-cols-4">
          <div>
            <Footer.Title title="Company" />
            <Footer.LinkGroup col>
              <Footer.Link href="#">About Us</Footer.Link>
              <Footer.Link href="#">Careers</Footer.Link>
              <Footer.Link href="#">Our Team</Footer.Link>
              <Footer.Link href="#">Blog</Footer.Link>
            </Footer.LinkGroup>
          </div>
          <div>
            <Footer.Title title="Help Center" />
            <Footer.LinkGroup col>
              <Footer.Link href="#">Contact Us</Footer.Link>
              <Footer.Link href="#">FAQ</Footer.Link>
              <Footer.Link href="#">Support</Footer.Link>
              <Footer.Link href="#">Feedback</Footer.Link>
            </Footer.LinkGroup>
          </div>
          <div>
            <Footer.Title title="Legal" />
            <Footer.LinkGroup col>
              <Footer.Link href="#">Privacy Policy</Footer.Link>
              <Footer.Link href="#">Terms & Conditions</Footer.Link>
              <Footer.Link href="#">Patient Rights</Footer.Link>
              <Footer.Link href="#">HIPAA Compliance</Footer.Link>
            </Footer.LinkGroup>
          </div>
          <div>
            <Footer.Title title="Resources" />
            <Footer.LinkGroup col>
              <Footer.Link href="#">Health Tips</Footer.Link>
              <Footer.Link href="#">Medical Resources</Footer.Link>
              <Footer.Link href="#">Find a Doctor</Footer.Link>
              <Footer.Link href="#">Appointment Guide</Footer.Link>
            </Footer.LinkGroup>
          </div>
        </div>
        <div className="w-full bg-gray-700 px-4 py-6 sm:flex sm:items-center sm:justify-between">
          <Footer.Copyright href="#" by="City Clinic System™" year={2024} />
          <div className="mt-4 flex space-x-6 sm:mt-0 sm:justify-center">
            <Footer.Icon href="#" icon={FaFacebook} />
            <Footer.Icon href="#" icon={FaInstagram} />
            <Footer.Icon href="#" icon={FaTwitter} />
            <Footer.Icon href="#" icon={FaGithub} />
            <Footer.Icon href="#" icon={FaDribbble} />
          </div>
        </div>
      </div>
    </Footer>
  );
}

export default FooterComp;
