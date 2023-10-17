import "./nav.css"
import DeloitteLogo from "./deloitte.svg"
const Nav = () => {
    return (
        <div className="nav-container">
                <img className="nav-logo" src={DeloitteLogo} alt="logo" />
                <div className="nav-title">Tax Auditor Assistant</div>
        </div>
    )
}

export default Nav