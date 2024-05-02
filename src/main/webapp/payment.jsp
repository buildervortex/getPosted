<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Payment Processing From</title>
        <link rel="stylesheet" href="css/payment.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    </head>
<body>
        <div class="wrapper">
            <div class="Payment">
                
                <h2>Payment Processing</h2>
                <div class="From">
                    <div class="card space icon-relative">
                        <label for="label">Card Holder:</label>
                        <input type="text"class="input"name="card_holder"placeholder="Coding Market">
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <div class="card space icon-relative">
                        <label for="label">Card Number:</label>
                        <input type="text"class="input"name="card_number"placeholder="Cade number" data-mask="0000 0000 0000 0000">
                        <i class="fa-solid fa-credit-card"></i>
                    </div>
                    <div class="Card_grp space">
                        <div class="card_iteam icon-relative">
                            <label for="label">Expriy Date:</label>
                            <input type="text"class="input"name="expriy_date"placeholder="00 / 00" data-mask="00 / 00">
                            <i class="fa-solid fa-calendar-days"></i>
                        </div>
                        <div class="card_iteam icon-relative">
                            <label for="label">CVC:</label>
                            <input type="text"class="input"name="CVC"placeholder="000" data-mask="000">
                            <i class="fa-solid fa-lock"></i>
                        </div>
                    </div>
                    <div class="button">
                        Pay
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
</body>
</html>