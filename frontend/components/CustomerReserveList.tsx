import "./CustomerReserveList.css"

export default function () {
    return (
        <div className="container" id="customer_reservations">
                    <table>
            <thead>
                <tr>
                    <th colSpan={5}>
                        My reservations
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr className="enabled">
                    <td id="date">2024-06-22 16:00</td>
                    <td id="restaurant"><a href="">Ali Daei Dizy</a></td>
                    <td id="table">Table-12</td>
                    <td id="seats">4 Seats</td>
                    <td className="action" id="Cancel"><a href="">Cancel</a></td>
                </tr>
                <tr className="disabled">
                    <td id="date">2024-06-22 16:00</td>
                    <td id="restaurant"><a href="">Ali Daei Dizy</a></td>
                    <td id="table">Table-12</td>
                    <td id="seats">4 Seats</td>
                    <td className="action" id="comment"><a href="">Add comment</a></td>
                </tr>
                <tr className="disabled">
                    <td id="date">2024-06-22 16:00</td>
                    <td id="restaurant"><a href="">Ali Daei Dizy</a></td>
                    <td id="table">Table-12</td>
                    <td id="seats">4 Seats</td>
                    <td className="action" id="comment"><a href="">Add comment</a></td>
                </tr>
                <tr className="disabled">
                    <td id="date">2024-06-22 16:00</td>
                    <td id="restaurant"><a href="">Ali Daei Dizy</a></td>
                    <td id="table">Table-12</td>
                    <td id="seats">4 Seats</td>
                    <td className="action" id="comment"><a href="">Add comment</a></td>
                </tr>
            </tbody>
        </table>

        </div>
    );
}