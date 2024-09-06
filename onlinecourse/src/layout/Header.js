import { useContext, useEffect, useState } from 'react';
import { Button, Col, Container, Form, Image, Nav, Navbar, NavDropdown, Row } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { MyDispatchContext, MyUserContext } from '../App';



const Header = () => {

    const user = useContext(MyUserContext)
    const dispatch = useContext(MyDispatchContext);
    const [kw, setKw] = useState("");
    const nav = useNavigate();

    const loadCates = async () => {
        try {

        } catch (ex) {
            console.error(ex);
        }
    }
    useEffect(() => {
        loadCates();
    }, []);

    const submit = (e) => {
        e.preventDefault();

        nav(`/?kw=${kw}`);
    }

    return (
        <>
            <Navbar expand="lg" className="bg-body-tertiary">
                <Container>
                    <Navbar.Brand href="#home">OnlineCourseWeb</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Link className='nav-link' to="/">Trang chủ</Link>

                            <NavDropdown title="Danh mục" id="basic-nav-dropdown">
                            </NavDropdown>

                            {user === null ? <>
                                <Link className='nav-link text-success' to="/login">&#129489; Đăng nhập</Link>
                                <Link className='nav-link text-success' to="/register">&#129489; Đăng ký</Link>


                            </> : <>
                                <Link className='nav-link text-success' to="/login">
                                    <Image src={user.avatar} width="25" roundedCircle />
                                    Chào {user.username}!</Link>
                                <Button variant='danger' onClick={() => dispatch({ "type": "logout" })}>Đăng xuất</Button>
                            </>}
                        </Nav>
                    </Navbar.Collapse>

                    <Form inline onSubmit={submit}>
                        <Row>
                            <Col xs="auto">
                                <Form.Control
                                    type="text"
                                    placeholder="Tìm sản phẩm..."
                                    className=" mr-sm-2"
                                    value={kw}
                                    onChange={e => setKw(e.target.value)}
                                />
                            </Col>
                            <Col xs="auto">
                                <Button type="submit">Tìm</Button>
                            </Col>
                        </Row>
                    </Form>
                </Container>
            </Navbar>
        </>
    );
}

export default Header;